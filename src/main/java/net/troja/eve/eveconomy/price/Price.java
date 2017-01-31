package net.troja.eve.eveconomy.price;

import javax.persistence.Entity;
import javax.persistence.Id;

import net.troja.eve.eveconomy.evecentral.EveCentralData;
import net.troja.eve.eveconomy.evecentral.EveCentralDataEntry;

@Entity
public class Price {
    @Id
    private int typeId;
    private long buyVolume;
    private double buyAvg;
    private double buyMedian;
    private float buyFivePercent;
    private float buyMax;
    private float buyMin;
    private long sellVolume;
    private double sellAvg;
    private double sellMedian;
    private float sellFivePercent;
    private float sellMax;
    private float sellMin;
    private long generated;

    public Price() {
        super();
    }

    public Price(final EveCentralData input) {
        this();
        overwrite(input);
    }

    public void overwrite(final EveCentralData input) {
        final EveCentralDataEntry buy = input.getBuy();
        typeId = buy.getForQuery().getTypes()[0];
        buyVolume = buy.getVolume();
        buyAvg = buy.getAvg();
        buyMedian = buy.getMedian();
        buyFivePercent = buy.getFivePercent();
        buyMax = buy.getMax();
        buyMin = buy.getMin();
        generated = buy.getGenerated();
        final EveCentralDataEntry sell = input.getSell();
        sellVolume = sell.getVolume();
        sellAvg = sell.getAvg();
        sellMedian = sell.getMedian();
        sellFivePercent = sell.getFivePercent();
        sellMax = sell.getMax();
        sellMin = sell.getMin();
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(final int typeId) {
        this.typeId = typeId;
    }

    public long getBuyVolume() {
        return buyVolume;
    }

    public void setBuyVolume(final long buyVolume) {
        this.buyVolume = buyVolume;
    }

    public double getBuyAvg() {
        return buyAvg;
    }

    public void setBuyAvg(final double buyAvg) {
        this.buyAvg = buyAvg;
    }

    public double getBuyMedian() {
        return buyMedian;
    }

    public void setBuyMedian(final double buyMedian) {
        this.buyMedian = buyMedian;
    }

    public float getBuyFivePercent() {
        return buyFivePercent;
    }

    public void setBuyFivePercent(final float buyFivePercent) {
        this.buyFivePercent = buyFivePercent;
    }

    public float getBuyMax() {
        return buyMax;
    }

    public void setBuyMax(final float buyMax) {
        this.buyMax = buyMax;
    }

    public float getBuyMin() {
        return buyMin;
    }

    public void setBuyMin(final float buyMin) {
        this.buyMin = buyMin;
    }

    public long getSellVolume() {
        return sellVolume;
    }

    public void setSellVolume(final long sellVolume) {
        this.sellVolume = sellVolume;
    }

    public double getSellAvg() {
        return sellAvg;
    }

    public void setSellAvg(final double sellAvg) {
        this.sellAvg = sellAvg;
    }

    public double getSellMedian() {
        return sellMedian;
    }

    public void setSellMedian(final double bsellMedian) {
        sellMedian = bsellMedian;
    }

    public float getSellFivePercent() {
        return sellFivePercent;
    }

    public void setSellFivePercent(final float sellFivePercent) {
        this.sellFivePercent = sellFivePercent;
    }

    public float getSellMax() {
        return sellMax;
    }

    public void setSellMax(final float sellMax) {
        this.sellMax = sellMax;
    }

    public float getSellMin() {
        return sellMin;
    }

    public void setSellMin(final float sellMin) {
        this.sellMin = sellMin;
    }

    public long getGenerated() {
        return generated;
    }

    public void setGenerated(final long generated) {
        this.generated = generated;
    }

    @Override
    public String toString() {
        return "Price [typeId=" + typeId + ", buyVolume=" + buyVolume + ", buyAvg=" + buyAvg + ", buyMedian="
                + buyMedian + ", buyFivePercent=" + buyFivePercent + ", buyMax=" + buyMax + ", buyMin=" + buyMin
                + ", sellVolume=" + sellVolume + ", sellAvg=" + sellAvg + ", bsellMedian=" + sellMedian
                + ", sellFivePercent=" + sellFivePercent + ", sellMax=" + sellMax + ", sellMin=" + sellMin
                + ", generated=" + generated + "]";
    }
}
