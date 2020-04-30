package ar.com.mercadolibre.riskanalysis.model;

import java.io.Serializable;

public class GeoLocalization implements Serializable {
    private static final long serialVersionUID = -9091242540423834859L;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryCode3() {
        return countryCode3;
    }

    public void setCountryCode3(String countryCode3) {
        this.countryCode3 = countryCode3;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryEmoji() {
        return countryEmoji;
    }

    public void setCountryEmoji(String countryEmoji) {
        this.countryEmoji = countryEmoji;
    }

    private String countryCode;
    private String countryCode3;
    private String countryName;
    private String countryEmoji;

    public GeoLocalization(String countryCode, String countryCode3, String countryName, String countryEmoji) {
        this.countryCode = countryCode;
        this.countryCode3 = countryCode3;
        this.countryName = countryName;
        this.countryEmoji = countryEmoji;
    }

    public GeoLocalization() {
    }
}
