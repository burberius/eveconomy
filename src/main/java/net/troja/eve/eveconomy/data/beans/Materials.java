package net.troja.eve.eveconomy.data.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Materials {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private int typeID;
    private String typeName;
    private int materialTypeID;
    private String materialTypeName;
    private boolean ice;
    private int quantity;

    public Materials() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
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

    public int getMaterialTypeID() {
        return materialTypeID;
    }

    public void setMaterialTypeID(final int materialTypeID) {
        this.materialTypeID = materialTypeID;
    }

    public String getMaterialTypeName() {
        return materialTypeName;
    }

    public void setMaterialTypeName(final String materialTypeName) {
        this.materialTypeName = materialTypeName;
    }

    public boolean isIce() {
        return ice;
    }

    public void setIce(final boolean ice) {
        this.ice = ice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Materials [id=" + id + ", typeID=" + typeID + ", typeName=" + typeName + ", materialTypeID="
                + materialTypeID + ", materialTypeName=" + materialTypeName + ", ice=" + ice + ", quantity=" + quantity
                + "]";
    }
}
