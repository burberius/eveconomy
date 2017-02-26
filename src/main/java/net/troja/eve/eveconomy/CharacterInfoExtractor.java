package net.troja.eve.eveconomy;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;

import net.troja.eve.eveconomy.account.Access;
import net.troja.eve.eveconomy.account.AccessRepository;
import net.troja.eve.eveconomy.account.Account;
import net.troja.eve.eveconomy.account.AccountRepository;

public class CharacterInfoExtractor implements PrincipalExtractor {
    private static final Logger LOGGER = LogManager.getLogger(CharacterInfoExtractor.class);

    private final AccessRepository accessRepository;
    private final AccountRepository accountRepository;

    public CharacterInfoExtractor(final AccessRepository accessRepository, final AccountRepository accountRepository) {
        this.accessRepository = accessRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Object extractPrincipal(final Map<String, Object> map) {
        final Integer characterId = (Integer) map.get("CharacterID");
        Access access = accessRepository.findOne(characterId);
        if (access == null) {
            final Account account = new Account();
            access = new Access();
            access.setAccount(accountRepository.save(account));
            access.setCharacterId(characterId);
            access.setCharacterName((String) map.get("CharacterName"));
            access.setCharacterOwnerHash((String) map.get("CharacterOwnerHash"));
            accessRepository.save(access);
            LOGGER.info("Saved new account " + access);
        }

        LOGGER.info("Current count " + accessRepository.count());

        return access;
    }

}
