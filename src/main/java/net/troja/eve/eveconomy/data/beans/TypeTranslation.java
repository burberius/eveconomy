package net.troja.eve.eveconomy.data.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TypeTranslation {
    @Id
    @GeneratedValue
    private int id;
    private int typeID;
    private String languageID;
    private String text;

    public TypeTranslation() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(final int typeID) {
        this.typeID = typeID;
    }

    public String getLanguageID() {
        return languageID;
    }

    public void setLanguageID(final String languageID) {
        this.languageID = languageID;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "TypeTranslation [typeID=" + typeID + ", languageID=" + languageID + ", text=" + text + "]";
    }
}
