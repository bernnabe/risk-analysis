package ar.com.mercadolibre.riskanalysis.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class Country implements Serializable {

    private static final long serialVersionUID = -7675518867828620723L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    public Set<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Set<Currency> currencies) {
        this.currencies = currencies;
    }

    private String name;
    private String alpha2Code;
    private String alpha3Code;
    private Set<Language> languages;
    private Set<Currency> currencies;
    private List<Double> latlng;

    public Country() {
    }

    public Country(String name, String alpha2Code, String alpha3Code, Set<Language> languages, Set<Currency> currencies, List<Double> latlng) {
        this.name = name;
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
        this.languages = languages;
        this.currencies = currencies;
        this.latlng = latlng;
    }

    public List<Double> getLatlng() {
        return latlng;
    }

    public void setLatlng(List<Double> latlng) {
        this.latlng = latlng;
    }
}
