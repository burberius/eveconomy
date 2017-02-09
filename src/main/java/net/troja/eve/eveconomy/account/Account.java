package net.troja.eve.eveconomy.account;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {
    @Id
    private int characterId;
    private String characterName;
    private String characterOwnerHash;
    private String email;
    private boolean verified;
    private Date created;
    private Date lastLogin;
    private boolean active = true;

    public Account() {
        super();
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(final int characterId) {
        this.characterId = characterId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(final String characterName) {
        this.characterName = characterName;
    }

    public String getCharacterOwnerHash() {
        return characterOwnerHash;
    }

    public void setCharacterOwnerHash(final String characterOwnerHash) {
        this.characterOwnerHash = characterOwnerHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(final boolean verified) {
        this.verified = verified;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(final Date created) {
        this.created = created;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(final Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Account [characterId=" + characterId + ", characterName=" + characterName + ", characterOwnerHash="
                + characterOwnerHash + ", email=" + email + ", verified=" + verified + ", created=" + created
                + ", lastLogin=" + lastLogin + ", active=" + active + "]";
    }
}
