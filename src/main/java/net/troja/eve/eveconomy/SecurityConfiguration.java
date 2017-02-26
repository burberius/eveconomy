package net.troja.eve.eveconomy;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import net.troja.eve.eveconomy.account.AccessRepository;
import net.troja.eve.eveconomy.account.AccountRepository;

@Configuration
@EnableOAuth2Client
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private OAuth2ClientContext oauth2ClientContext;
    @Autowired
    private AuthorizationCodeResourceDetails oauth2Client;
    @Autowired
    private ResourceServerProperties resourceProperties;
    @Autowired
    private AccessRepository accessRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.antMatcher("/**").authorizeRequests().antMatchers("/", "/login**", "/webjars/**").permitAll().anyRequest()
                .authenticated().and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").permitAll().and().csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
                .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
    }

    private Filter ssoFilter() {
        final OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(
                "/login");
        final OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(oauth2Client, oauth2ClientContext);
        filter.setRestTemplate(restTemplate);
        final UserInfoTokenServices tokenServices = new UserInfoTokenServices(resourceProperties.getUserInfoUri(),
                oauth2Client.getClientId());
        tokenServices.setRestTemplate(restTemplate);
        tokenServices.setPrincipalExtractor(new CharacterInfoExtractor(accessRepository, accountRepository));
        filter.setTokenServices(tokenServices);
        return filter;
    }
}
