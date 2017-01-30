package net.troja.eve.eveconomy.evecentral;

public class EveCentralDataEntry {
    private long volume;
    private double wavg;
    private double avg;
    private double variance;
    private double stdDev;
    private double median;
    private float fivePercent;
    private float max;
    private float min;
    private boolean highToLow;
    private long generated;
    private EveCentralForQuery forQuery;

    public EveCentralDataEntry() {
        super();
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(final long volume) {
        this.volume = volume;
    }

    public double getWavg() {
        return wavg;
    }

    public void setWavg(final double wavg) {
        this.wavg = wavg;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(final double avg) {
        this.avg = avg;
    }

    public double getVariance() {
        return variance;
    }

    public void setVariance(final double variance) {
        this.variance = variance;
    }

    public double getStdDev() {
        return stdDev;
    }

    public void setStdDev(final double stdDev) {
        this.stdDev = stdDev;
    }

    public double getMedian() {
        return median;
    }

    public void setMedian(final double median) {
        this.median = median;
    }

    public float getFivePercent() {
        return fivePercent;
    }

    public void setFivePercent(final float fivePercent) {
        this.fivePercent = fivePercent;
    }

    public float getMax() {
        return max;
    }

    public void setMax(final float max) {
        this.max = max;
    }

    public float getMin() {
        return min;
    }

    public void setMin(final float min) {
        this.min = min;
    }

    public boolean isHighToLow() {
        return highToLow;
    }

    public void setHighToLow(final boolean highToLow) {
        this.highToLow = highToLow;
    }

    public long getGenerated() {
        return generated;
    }

    public void setGenerated(final long generated) {
        this.generated = generated;
    }

    public EveCentralForQuery getForQuery() {
        return forQuery;
    }

    public void setForQuery(final EveCentralForQuery forQuery) {
        this.forQuery = forQuery;
    }

    @Override
    public String toString() {
        return "EveCentralDataEntry [volume=" + volume + ", wavg=" + wavg + ", avg=" + avg + ", variance=" + variance
                + ", stdDev=" + stdDev + ", median=" + median + ", fivePercent=" + fivePercent + ", max=" + max
                + ", min=" + min + ", highToLow=" + highToLow + ", generated=" + generated + ", forQuery=" + forQuery
                + "]";
    }
}
