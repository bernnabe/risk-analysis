package ar.com.mercadolibre.riskanalysis.repository.impl;

import ar.com.mercadolibre.riskanalysis.model.Country;
import ar.com.mercadolibre.riskanalysis.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CountryBaseRepositoryImpl extends BaseRepository<Country>
        implements CountryRepository {
    private static final String CACHE_NAME = "country-single";
    private static final String CACHE_KEY = "#code";
    private final RestTemplate restTemplate;

    @Value("${risk-analysis.repository.apis.api-countryinfo.url}")
    private String apiUrl;

    public CountryBaseRepositoryImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    @Cacheable(value = CACHE_NAME, key = CACHE_KEY)
    public Country find(String code) {
        Map<String, String> params = new HashMap<>();
        params.put("code", code);

        return restTemplate.getForObject(apiUrl, Country.class, params);
    }
}
