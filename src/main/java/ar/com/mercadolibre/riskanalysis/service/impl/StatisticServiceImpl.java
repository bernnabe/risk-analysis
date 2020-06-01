package ar.com.mercadolibre.riskanalysis.service.impl;

import ar.com.mercadolibre.riskanalysis.model.Statistic;
import ar.com.mercadolibre.riskanalysis.model.StatisticItem;
import ar.com.mercadolibre.riskanalysis.model.StatisticScore;
import ar.com.mercadolibre.riskanalysis.repository.StatisticRepository;
import ar.com.mercadolibre.riskanalysis.service.StatisticService;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

@Service
public class StatisticServiceImpl implements StatisticService {

    private final StatisticRepository statisticRepository;
    private final StatisticScoreComparator statisticScoreComparator;

    public StatisticServiceImpl(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
        this.statisticScoreComparator = new StatisticScoreComparator();
    }

    @Override
    public void incrementScore(StatisticItem item) {
        this.statisticRepository.incrementScore(item);
    }

    @Override
    public Statistic find() throws IOException {
        final Collection<StatisticScore> scores = this.statisticRepository.findScores().values();
        final StatisticScore minScore = Collections.min(scores, this.statisticScoreComparator);
        final StatisticScore maxScore = Collections.max(scores, this.statisticScoreComparator);
        final double averageDistance = getAverageDistance(scores);

        Statistic statistic = new Statistic();
        statistic.setMaxScore(maxScore);
        statistic.setMinScore(minScore);
        statistic.setAverageDistance(averageDistance);

        return statistic;
    }

    private double getAverageDistance(Collection<StatisticScore> scores) {
        double totalHitCount = 0.0;
        double totalDistance = 0.0;

        for (StatisticScore score : scores) {
            totalDistance += score.getDistance() * score.getHitCount();
            totalHitCount += score.getHitCount();
        }

        if (totalHitCount == 0.0) return 0.0;

        return Precision.round(totalDistance / totalHitCount, 2);
    }

    public static class StatisticScoreComparator implements Comparator<StatisticScore> {
        @Override
        public int compare(StatisticScore s1, StatisticScore s2) {
            return s1.getHitCount().compareTo(s2.getHitCount());
        }
    }
}
