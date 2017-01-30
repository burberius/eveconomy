package net.troja.eve.eveconomy.evecentral;

import java.util.Arrays;

public class EveCentralForQuery {
    private boolean bid;
    private int[] types;
    private long[] regions;
    private long[] systems;
    private int hours;
    private int minq;

    public EveCentralForQuery() {
        super();
    }

    public boolean isBid() {
        return bid;
    }

    public void setBid(final boolean bid) {
        this.bid = bid;
    }

    public int[] getTypes() {
        return types;
    }

    public void setTypes(final int[] types) {
        this.types = types;
    }

    public long[] getRegions() {
        return regions;
    }

    public void setRegions(final long[] regions) {
        this.regions = regions;
    }

    public long[] getSystems() {
        return systems;
    }

    public void setSystems(final long[] systems) {
        this.systems = systems;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(final int hours) {
        this.hours = hours;
    }

    public int getMinq() {
        return minq;
    }

    public void setMinq(final int minq) {
        this.minq = minq;
    }

    @Override
    public String toString() {
        return "EveCentralForQuery [bid=" + bid + ", types=" + Arrays.toString(types) + ", regions="
                + Arrays.toString(regions) + ", systems=" + Arrays.toString(systems) + ", hours=" + hours + ", minq="
                + minq + "]";
    }

}
