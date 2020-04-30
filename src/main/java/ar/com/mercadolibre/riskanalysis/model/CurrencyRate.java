package ar.com.mercadolibre.riskanalysis.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

public class CurrencyRate implements Serializable {
    private static final long serialVersionUID = -1431588261609715588L;

    public CurrencyRate(String base, LocalDate date, Map<String, Double> rates) {
        this.base = base;
        this.date = date;
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    private String base;
    private LocalDate date;
    private Map<String, Double> rates;
}
