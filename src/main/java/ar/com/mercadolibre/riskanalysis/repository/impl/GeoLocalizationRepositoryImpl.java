package ar.com.mercadolibre.riskanalysis.repository.impl;

import ar.com.mercadolibre.riskanalysis.model.GeoLocalization;
import ar.com.mercadolibre.riskanalysis.repository.GeoLocalizationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Repository
public class GeoLocalizationRepositoryImpl extends BaseRepository<GeoLocalization> implements GeoLocalizationRepository {
    private final RestTemplate restTemplate;

    @Value("${risk-analysis.repository.apis.api-geolocalization.url}")
    private String apiUrl;

    public GeoLocalizationRepositoryImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public GeoLocalization find(String ip) {
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);

        return restTemplate.getForObject(apiUrl, GeoLocalization.class, params);
    }
}
