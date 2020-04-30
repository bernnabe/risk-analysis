package ar.com.mercadolibre.riskanalysis.model;

import java.io.Serializable;

public class Language implements Serializable {
    private static final long serialVersionUID = 4852649627576569331L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Language() {

    }

    public Language(String name) {
        this.name = name;
    }
}
