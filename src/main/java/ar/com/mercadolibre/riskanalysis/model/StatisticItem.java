package ar.com.mercadolibre.riskanalysis.model;

import java.io.Serializable;

public class StatisticItem implements Serializable {
    public StatisticItem(String countryCode, Double distance) {
        this.countryCode = countryCode;
        this.distance = distance;
    }

    private String countryCode;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    private Double distance;
}