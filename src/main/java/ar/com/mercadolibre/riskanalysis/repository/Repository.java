package ar.com.mercadolibre.riskanalysis.repository;

public interface Repository<T> {
    T find(String arg);
}
