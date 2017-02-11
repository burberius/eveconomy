package net.troja.eve.eveconomy.data.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Type {
    @Id
    private int typeID;
    private String typeName;
    private double volume;
    private int iconID;
    private int groupID;
    private String groupName;

    public Type() {
        super();
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(final int typeID) {
        this.typeID = typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(final String typeName) {
        this.typeName = typeName;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(final double volume) {
        this.volume = volume;
    }

    public int getIconID() {
        return iconID;
    }

    public void setIconID(final int iconID) {
        this.iconID = iconID;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(final int groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(final String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "Type [typeID=" + typeID + ", typeName=" + typeName + ", volume=" + volume + ", iconID=" + iconID
                + ", groupID=" + groupID + ", groupName=" + groupName + "]";
    }
}
