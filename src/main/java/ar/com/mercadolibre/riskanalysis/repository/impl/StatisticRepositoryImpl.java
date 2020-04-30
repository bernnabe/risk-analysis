package ar.com.mercadolibre.riskanalysis.repository.impl;

import ar.com.mercadolibre.riskanalysis.model.Statistic;
import ar.com.mercadolibre.riskanalysis.model.StatisticItem;
import ar.com.mercadolibre.riskanalysis.model.StatisticScore;
import ar.com.mercadolibre.riskanalysis.repository.StatisticRepository;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class StatisticRepositoryImpl extends BaseRepository<Statistic> implements StatisticRepository {
    private static final String TABLE_KEY = "Statistic";
    private static final double SCORE_INCREMENT_VALUE = 1.0;
    private final RedisTemplate<String, StatisticItem> redisTemplate;

    public StatisticRepositoryImpl(RedisTemplate<String, StatisticItem> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void incrementScore(StatisticItem item) {

        redisTemplate.opsForZSet().incrementScore(TABLE_KEY, item, SCORE_INCREMENT_VALUE);
    }

    @Override
    public Map<String, StatisticScore> findScores() throws IOException {
        final ScanOptions scanOptions = ScanOptions.scanOptions().build();
        final Cursor<ZSetOperations.TypedTuple<StatisticItem>> statistics = redisTemplate.opsForZSet().scan(TABLE_KEY, scanOptions);

        Map<String, StatisticScore> results = new HashMap<>();
        StatisticScore result;
        ZSetOperations.TypedTuple<StatisticItem> statistic;
        StatisticItem value;

        while (statistics.hasNext()) {
            statistic = statistics.next();
            value = statistic.getValue();

            result = new StatisticScore();
            result.setIsoCode(value.getCountryCode());
            result.setDistance(value.getDistance());
            result.setHitCount(statistic.getScore());
            results.put(value.getCountryCode(), result);
        }

        statistics.close();

        return results;
    }

    @Override
    public Statistic find(String arg) {
        return null;
    }
}
