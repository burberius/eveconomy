package net.troja.eve.eveconomy.pve;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EventLoot {
    @Id
    @GeneratedValue
    private long id;
    private int typeId;
    private String typeName;
    private int quantity;
    private double price;

    public EventLoot() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(final int typeId) {
        this.typeId = typeId;
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
        return "EventLoot [id=" + id + ", typeId=" + typeId + ", typeName=" + typeName + ", quantity=" + quantity
                + ", price=" + price + "]";
    }
}
