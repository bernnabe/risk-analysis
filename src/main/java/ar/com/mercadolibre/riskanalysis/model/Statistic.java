package ar.com.mercadolibre.riskanalysis.model;

public class Statistic {

    public StatisticScore getMinScore() {
        return minScore;
    }

    public void setMinScore(StatisticScore minScore) {
        this.minScore = minScore;
    }

    public StatisticScore getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(StatisticScore maxScore) {
        this.maxScore = maxScore;
    }

    public Double getAverageDistance() {
        return averageDistance;
    }

    public void setAverageDistance(Double averageDistance) {
        this.averageDistance = averageDistance;
    }

    private StatisticScore minScore;
    private StatisticScore maxScore;
    private Double averageDistance;


}
