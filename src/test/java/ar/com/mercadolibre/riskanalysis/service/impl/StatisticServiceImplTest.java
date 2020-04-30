package ar.com.mercadolibre.riskanalysis.service.impl;

import ar.com.mercadolibre.riskanalysis.model.Statistic;
import ar.com.mercadolibre.riskanalysis.model.StatisticScore;
import ar.com.mercadolibre.riskanalysis.repository.StatisticRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class StatisticServiceImplTest {

    @InjectMocks
    private StatisticServiceImpl statisticService;

    @Mock
    private StatisticRepository statisticRepository;

    @BeforeEach
    public void beforeAll() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void when_statistic_is_saved() throws IOException {
        Map<String, StatisticScore> scores = new HashMap<>();
        scores.put("BR", new StatisticScore("BR", 2862.0, 10.0));
        scores.put("ES", new StatisticScore("ES", 10040.0, 5.0));

        when(this.statisticRepository.findScores()).thenReturn(scores);

        Statistic statistic = this.statisticService.find();

        Double distance = 5254.67;
        Double maxScoreHitCount = 10.0;
        Double minScoreHitCount = 5.0;
        Double maxScoreDistance = 2862.0;
        Double minScoreDistance = 10040.0;

        assertEquals(distance, statistic.getAverageDistance());
        assertEquals(maxScoreHitCount, statistic.getMaxScore().getHitCount());
        assertEquals(minScoreHitCount, statistic.getMinScore().getHitCount());
        assertEquals("BR", statistic.getMaxScore().getIsoCode());
        assertEquals("ES", statistic.getMinScore().getIsoCode());
        assertEquals(maxScoreDistance, statistic.getMaxScore().getDistance());
        assertEquals(minScoreDistance, statistic.getMinScore().getDistance());
    }
}
