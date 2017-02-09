package net.troja.eve.eveconomy;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import net.troja.eve.eveconomy.account.Account;
import net.troja.eve.eveconomy.account.AccountRepository;

public class CharacterInfoExtractorTest {
    private CharacterInfoExtractor classToTest;

    @Mock
    private AccountRepository repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        classToTest = new CharacterInfoExtractor(repository);
    }

    @Test
    public void extractPrincipal() {
        final int characterId = 12345;
        final String name = "Noname";
        final String hash = "Hash";

        final Map<String, Object> map = new HashMap<>();
        map.put("CharacterID", characterId);
        map.put("CharacterName", name);
        map.put("CharacterOwnerHash", hash);

        when(repository.findOne(characterId)).thenReturn(null);

        final Account account = (Account) classToTest.extractPrincipal(map);

        verify(repository).save(account);
        assertThat(account.getCharacterId(), equalTo(characterId));
        assertThat(account.getCharacterName(), equalTo(name));
        assertThat(account.getCharacterOwnerHash(), equalTo(hash));
    }
}
