package ar.com.mercadolibre.riskanalysis.model;


import java.io.Serializable;
import java.time.LocalDateTime;

public class TraceIpResult implements Serializable {

    private static final long serialVersionUID = 2097797103029678829L;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    private String ip;
    private LocalDateTime date;
    private String country;
    private String isoCode;
    private String language;
    private String currency;
    private Double distance;

    public TraceIpResult() {
    }

    public TraceIpResult(String ip, LocalDateTime date, String country, String isoCode, String language, String currency, Double distance) {
        this.ip = ip;
        this.date = date;
        this.country = country;
        this.isoCode = isoCode;
        this.currency = currency;
        this.distance = distance;
        this.language = language;
    }
}
