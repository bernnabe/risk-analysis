package ar.com.mercadolibre.riskanalysis.model;

public class StatisticScore {

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getHitCount() {
        return hitCount;
    }

    public void setHitCount(Double hitCount) {
        this.hitCount = hitCount;
    }

    private String isoCode;
    private Double distance;
    private Double hitCount;

    public StatisticScore() {

    }

    public StatisticScore(String isoCode, Double distance, Double hitCount) {
        this.isoCode = isoCode;
        this.distance = distance;
        this.hitCount = hitCount;
    }
}
