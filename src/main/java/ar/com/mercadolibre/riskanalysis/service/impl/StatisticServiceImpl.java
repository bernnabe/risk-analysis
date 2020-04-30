package ar.com.mercadolibre.riskanalysis.service.impl;

import ar.com.mercadolibre.riskanalysis.model.Statistic;
import ar.com.mercadolibre.riskanalysis.model.StatisticItem;
import ar.com.mercadolibre.riskanalysis.model.StatisticScore;
import ar.com.mercadolibre.riskanalysis.repository.StatisticRepository;
import ar.com.mercadolibre.riskanalysis.service.StatisticService;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

@Service
public class StatisticServiceImpl implements StatisticService {

    private final StatisticRepository statisticRepository;

    public StatisticServiceImpl(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    @Override
    public void incrementScore(StatisticItem item) {
        this.statisticRepository.incrementScore(item);
    }

    @Override
    public Statistic find() throws IOException {
        final Map<String, StatisticScore> scores = this.statisticRepository.findScores();
        final StatisticScoreComparator comparator = new StatisticScoreComparator();
        final StatisticScore minScore = Collections.min(scores.values(), comparator);
        final StatisticScore maxScore = Collections.max(scores.values(), comparator);
        final double averageDistance = getAverageDistance(scores);

        Statistic statistic = new Statistic();
        statistic.setMaxScore(maxScore);
        statistic.setMinScore(minScore);
        statistic.setAverageDistance(averageDistance);

        return statistic;
    }

    private double getAverageDistance(Map<String, StatisticScore> scores) {
        double totalHitCount = 0.0;
        double totalDistance = 0.0;

        for (StatisticScore score : scores.values()) {
            totalDistance += score.getDistance() * score.getHitCount();
            totalHitCount += score.getHitCount();
        }

        if (totalHitCount == 0.0) return 0.0;

        return Precision.round(totalDistance / totalHitCount, 2);
    }

    public class StatisticScoreComparator implements Comparator<StatisticScore> {
        @Override
        public int compare(StatisticScore s1, StatisticScore s2) {
            return s1.getHitCount().compareTo(s2.getHitCount());
        }
    }
}
