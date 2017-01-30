package net.troja.eve.eveconomy.evecentral;

public class EveCentralData {
    private EveCentralDataEntry buy;
    private EveCentralDataEntry all;
    private EveCentralDataEntry sell;

    public EveCentralData() {
        super();
    }

    public EveCentralDataEntry getBuy() {
        return buy;
    }

    public void setBuy(final EveCentralDataEntry buy) {
        this.buy = buy;
    }

    public EveCentralDataEntry getAll() {
        return all;
    }

    public void setAll(final EveCentralDataEntry all) {
        this.all = all;
    }

    public EveCentralDataEntry getSell() {
        return sell;
    }

    public void setSell(final EveCentralDataEntry sell) {
        this.sell = sell;
    }

    @Override
    public String toString() {
        return "EveCentralData [buy=" + buy + ", all=" + all + ", sell=" + sell + "]";
    }
}
