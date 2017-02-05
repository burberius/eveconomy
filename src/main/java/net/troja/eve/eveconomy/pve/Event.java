package net.troja.eve.eveconomy.pve;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Event {
    @Id
    @GeneratedValue
    private long id;
    private Date start;
    private Date end;
    private String name;
    private List<String> partner;
    private String comment;
    @ManyToOne
    private List<EventLoot> loot;
    private double lootPrice;

    public Event() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(final Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(final Date end) {
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<String> getPartner() {
        return partner;
    }

    public void setPartner(final List<String> partner) {
        this.partner = partner;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public List<EventLoot> getLoot() {
        return loot;
    }

    public void setLoot(final List<EventLoot> loot) {
        this.loot = loot;
    }

    public double getLootPrice() {
        return lootPrice;
    }

    public void setLootPrice(final double lootPrice) {
        this.lootPrice = lootPrice;
    }

    @Override
    public String toString() {
        return "Event [id=" + id + ", start=" + start + ", end=" + end + ", name=" + name + ", partner=" + partner
                + ", comment=" + comment + ", loot=" + loot + ", lootPrice=" + lootPrice + "]";
    }
}
