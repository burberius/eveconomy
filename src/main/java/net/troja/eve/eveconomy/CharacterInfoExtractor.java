package net.troja.eve.eveconomy;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;

import net.troja.eve.eveconomy.account.Account;
import net.troja.eve.eveconomy.account.AccountRepository;

public class CharacterInfoExtractor implements PrincipalExtractor {
    private static final Logger LOGGER = LogManager.getLogger(CharacterInfoExtractor.class);

    private final AccountRepository repository;

    public CharacterInfoExtractor(final AccountRepository accountRepository) {
        repository = accountRepository;
    }

    @Override
    public Object extractPrincipal(final Map<String, Object> map) {
        final Integer characterId = (Integer) map.get("CharacterID");
        Account account = repository.findOne(characterId);
        if (account == null) {
            account = new Account();
            account.setCharacterId(characterId);
            account.setCharacterName((String) map.get("CharacterName"));
            account.setCharacterOwnerHash((String) map.get("CharacterOwnerHash"));
            repository.save(account);
            LOGGER.info("Saved new account " + account);
        }

        LOGGER.info("Current count " + repository.count());

        return account;
    }

}
