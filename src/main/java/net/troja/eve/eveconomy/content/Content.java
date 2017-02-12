package net.troja.eve.eveconomy.content;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Content {
    @Id
    @GeneratedValue
    private long id;
    private int typeID;
    private String typeName;
    private int quantity;
    private double price;

    public Content() {
        super();
    }

    public Content(final int typeID, final String typeName, final int quantity) {
        super();
        this.typeID = typeID;
        this.typeName = typeName;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Content [typeID=" + typeID + ", typeName=" + typeName + ", quantity=" + quantity + ", price=" + price
                + "]";
    }
}
