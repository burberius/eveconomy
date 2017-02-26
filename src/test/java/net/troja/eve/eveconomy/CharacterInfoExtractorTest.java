package net.troja.eve.eveconomy;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import net.troja.eve.eveconomy.account.Access;
import net.troja.eve.eveconomy.account.AccessRepository;
import net.troja.eve.eveconomy.account.Account;
import net.troja.eve.eveconomy.account.AccountRepository;

public class CharacterInfoExtractorTest {
    private CharacterInfoExtractor classToTest;

    @Mock
    private AccessRepository accessRepository;
    @Mock
    private AccountRepository accountRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        classToTest = new CharacterInfoExtractor(accessRepository, accountRepository);
    }

    @Test
    public void extractPrincipal() {
        final int characterId = 12345;
        final String name = "Noname";
        final String hash = "Hash";
        final Account account = new Account();

        final Map<String, Object> map = new HashMap<>();
        map.put("CharacterID", characterId);
        map.put("CharacterName", name);
        map.put("CharacterOwnerHash", hash);

        when(accessRepository.findOne(characterId)).thenReturn(null);
        when(accountRepository.save((Account) any())).thenReturn(account);

        final Access access = (Access) classToTest.extractPrincipal(map);

        verify(accessRepository).save(access);
        assertThat(access.getCharacterId(), equalTo(characterId));
        assertThat(access.getCharacterName(), equalTo(name));
        assertThat(access.getCharacterOwnerHash(), equalTo(hash));
        assertThat(access.getAccount(), equalTo(account));
    }
}
