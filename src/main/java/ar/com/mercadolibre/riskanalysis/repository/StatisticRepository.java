package ar.com.mercadolibre.riskanalysis.repository;

import ar.com.mercadolibre.riskanalysis.model.Statistic;
import ar.com.mercadolibre.riskanalysis.model.StatisticItem;
import ar.com.mercadolibre.riskanalysis.model.StatisticScore;

import java.io.IOException;
import java.util.Map;

public interface StatisticRepository extends Repository<Statistic> {
    void incrementScore(StatisticItem item);

    Map<String, StatisticScore> findScores() throws IOException;
}
