package net.troja.eve.eveconomy.account;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Access {
    @Id
    private int characterId;
    private String characterName;
    private String characterOwnerHash;
    private Date created = new Date();
    private Date lastLogin;
    private boolean active = true;
    private String refreshToken;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    public Access() {
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

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(final Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Access [characterId=" + characterId + ", characterName=" + characterName + ", characterOwnerHash="
                + characterOwnerHash + ", created=" + created + ", lastLogin=" + lastLogin + ", active=" + active
                + ", refreshToken=" + refreshToken + ", account=" + account + "]";
    }
}
