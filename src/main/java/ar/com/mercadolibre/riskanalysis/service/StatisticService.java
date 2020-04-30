package ar.com.mercadolibre.riskanalysis.service;

import ar.com.mercadolibre.riskanalysis.model.Statistic;
import ar.com.mercadolibre.riskanalysis.model.StatisticItem;

import java.io.IOException;

public interface StatisticService {

    void incrementScore(StatisticItem item);

    Statistic find() throws IOException;
}
