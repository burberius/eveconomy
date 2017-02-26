package net.troja.eve.eveconomy.account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private int accountId;
    private Date generated = new Date();
    @OneToMany(mappedBy = "account")
    private List<Access> accesses = new ArrayList<>();
    private String email;
    private boolean verified;

    public Date getGenerated() {
        return generated;
    }

    public void setGenerated(final Date generated) {
        this.generated = generated;
    }

    public List<Access> getAccesses() {
        return accesses;
    }

    public void setAccesses(final List<Access> accesses) {
        this.accesses = accesses;
    }

    public void addAccess(final Access access) {
        accesses.add(access);
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

    @Override
    public String toString() {
        return "Account [accountId=" + accountId + ", generated=" + generated + ", accesses=" + accesses + ", email="
                + email + ", verified=" + verified + "]";
    }

}
