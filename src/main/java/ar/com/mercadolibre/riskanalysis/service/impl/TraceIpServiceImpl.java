package ar.com.mercadolibre.riskanalysis.service.impl;

import ar.com.mercadolibre.riskanalysis.exception.BusinessException;
import ar.com.mercadolibre.riskanalysis.model.*;
import ar.com.mercadolibre.riskanalysis.repository.CountryRepository;
import ar.com.mercadolibre.riskanalysis.repository.CurrencyRateRepository;
import ar.com.mercadolibre.riskanalysis.repository.GeoLocalizationRepository;
import ar.com.mercadolibre.riskanalysis.service.TraceIpService;
import ar.com.mercadolibre.riskanalysis.service.util.DistanceCalculator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TraceIpServiceImpl implements TraceIpService {

    private static final String CURRENCY_DESCRIPTION_FORMAT = "%s (1 %s = %s %s)";
    private static final String LANGUAGE_DESCRIPTION_SEPARATOR = ", ";
    private static final String TRACE_RESULT_CACHE_NAME = "trace-results";
    private static final String CACHE_KEY = "#ip";
    private static final String IP_VALIDATION_MESSAGE = "Invalid Ip";
    private static final String COUNTRY_CODE_VALIDATION_MESSAGE = "Invalid Country Code";

    @Value("${risk-analysis.service.trace-ip-service.argentina-code}")
    private String argentinaCode;

    private final CountryRepository countryRepository;
    private final CurrencyRateRepository currencyRateRepository;
    private final GeoLocalizationRepository geoLocalizationRepository;


    public TraceIpServiceImpl(CountryRepository countryRepository,
                              CurrencyRateRepository currencyRateRepository,
                              GeoLocalizationRepository geoLocalizationRepository) {
        this.countryRepository = countryRepository;
        this.currencyRateRepository = currencyRateRepository;
        this.geoLocalizationRepository = geoLocalizationRepository;
    }

    @Cacheable(value = TRACE_RESULT_CACHE_NAME, key = CACHE_KEY)
    public TraceIpResult getTraceIp(String ip) {
        final GeoLocalization geoLocalization = this.geoLocalizationRepository.find(ip);

        validateGeolocalization(geoLocalization);

        final Country country = this.countryRepository.find(geoLocalization.getCountryCode());
        String currencyCode = "";
        final Optional<Currency> d = country.getCurrencies()
                .stream()
                .findFirst();

        if (d.isPresent()) {
            currencyCode = d.get().getCode();
        }

        final CurrencyRate currencyRate = this.currencyRateRepository.find(currencyCode);
        final String currencyDescription = getCurrencyDescription(currencyCode, currencyRate);
        final String languagesDescription = getLanguageDescription(country);
        final Double distance = getDistance(country);

        TraceIpResult result = new TraceIpResult();
        result.setCountry(country.getName());
        result.setIsoCode(country.getAlpha3Code());
        result.setCurrency(currencyDescription);
        result.setLanguage(languagesDescription);
        result.setDate(LocalDateTime.now());
        result.setDistance(distance);
        result.setIp(ip);

        return result;
    }

    private void validateGeolocalization(GeoLocalization geoLocalization) {
        if (geoLocalization == null) throw new BusinessException(IP_VALIDATION_MESSAGE);
        if ("".equals(geoLocalization.getCountryCode())) throw new BusinessException(COUNTRY_CODE_VALIDATION_MESSAGE);
    }

    private Double getDistance(Country country) {
        final Country argentina = this.countryRepository.find(this.argentinaCode);
        return DistanceCalculator.calculate(argentina.getLatlng(), country.getLatlng());
    }

    private String getLanguageDescription(Country country) {
        return country.getLanguages()
                .stream()
                .map(a -> String.valueOf(a.getName()))
                .collect(Collectors.joining(LANGUAGE_DESCRIPTION_SEPARATOR));
    }

    private String getCurrencyDescription(String currencyCode, CurrencyRate currencyRate) {
        return String.format(CURRENCY_DESCRIPTION_FORMAT,
                currencyCode,
                currencyRate.getBase(),
                currencyRate.getRates().get(currencyCode),
                currencyCode);
    }
}
