package ar.com.mercadolibre.riskanalysis.repository.impl;

import ar.com.mercadolibre.riskanalysis.model.CurrencyRate;
import ar.com.mercadolibre.riskanalysis.repository.CurrencyRateRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Repository
public class CurrencyRateRepositoryImpl extends BaseRepository<CurrencyRate> implements CurrencyRateRepository {
    public static final String CACHE_NAME = "currency-rate-single";
    public static final String CACHE_KEY = "#code";
    private final RestTemplate restTemplate;

    @Value("${risk-analysis.repository.apis.api-currencyinfo.url}")
    private String apiUrl;

    @Value("${risk-analysis.repository.apis.api-currencyinfo.access-key}")
    private String accessKey;

    @Value("${risk-analysis.repository.apis.api-currencyinfo.base-currency}")
    private String base;

    public CurrencyRateRepositoryImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    @Cacheable(value = CACHE_NAME, key = CACHE_KEY)
    public CurrencyRate find(String code) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("symbols", code)
                .queryParam("base", this.base)
                .queryParam("access_key", accessKey);

        return restTemplate.getForObject(builder.toUriString(), CurrencyRate.class);
    }
}
