package net.troja.eve.eveconomy;

import java.util.Map;

import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;

import net.troja.eve.esi.auth.CharacterInfo;

public class CharacterInfoExtractor implements PrincipalExtractor {

    @Override
    public Object extractPrincipal(final Map<String, Object> map) {
        final CharacterInfo characterInfo = new CharacterInfo();
        characterInfo.setCharacterId((Integer) map.get("CharacterID"));
        characterInfo.setCharacterName((String) map.get("CharacterName"));
        characterInfo.setCharacterOwnerHash((String) map.get("CharacterOwnerHash"));
        characterInfo.setTokenType((String) map.get("TokenType"));
        return characterInfo;
    }

}
