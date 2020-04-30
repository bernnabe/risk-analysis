package ar.com.mercadolibre.riskanalysis.service.impl;

import ar.com.mercadolibre.riskanalysis.model.Currency;
import ar.com.mercadolibre.riskanalysis.model.*;
import ar.com.mercadolibre.riskanalysis.repository.CountryRepository;
import ar.com.mercadolibre.riskanalysis.repository.CurrencyRateRepository;
import ar.com.mercadolibre.riskanalysis.repository.GeoLocalizationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
class TraceIpServiceImplTest {

    @InjectMocks
    TraceIpServiceImpl traceIpService;

    @Mock
    GeoLocalizationRepository geoLocalizationRepository;

    @Mock
    CountryRepository countryRepository;

    @Mock
    CurrencyRateRepository currencyRateRepository;

    @BeforeEach
    public void beforeAll() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(this.traceIpService, "argentinaCode", "AR");
    }

    @Test
    public void when_traceip_is_invoked() {
        final String brasilCountryCode = "BRA";
        Set<Currency> currencies = new HashSet<>();
        Set<Language> languages = new HashSet<>();
        List<Double> latlngBrasil = new ArrayList<>();
        List<Double> latlngArgentina = new ArrayList<>();

        languages.add(new Language("Portuguese"));
        currencies.add(new Currency("BRL", "Brazilian real", "R$"));
        latlngBrasil.add(-10.0);
        latlngBrasil.add(-55.0);
        latlngArgentina.add(-34.0);
        latlngArgentina.add(-64.0);

        String iptoTrace = "1.2.3.4";
        Country brasil = new Country("Brasil", brasilCountryCode, brasilCountryCode, languages, currencies, latlngBrasil);
        Country argentina = new Country("Argentina", "AR", "AR", null, null, latlngArgentina);

        GeoLocalization geoLocalization = new GeoLocalization(brasilCountryCode, brasilCountryCode, "Brasil", "");
        Map<String, Double> rates = new HashMap<>();
        rates.put("BRL", 6.041236);
        CurrencyRate currencyRate = new CurrencyRate("EUR", LocalDate.now(), rates);

        when(this.geoLocalizationRepository.find(iptoTrace)).thenReturn(geoLocalization);
        when(this.countryRepository.find(brasilCountryCode)).thenReturn(brasil);
        when(this.countryRepository.find("AR")).thenReturn(argentina);
        when(this.currencyRateRepository.find("BRL")).thenReturn(currencyRate);

        TraceIpResult result = traceIpService.getTraceIp(iptoTrace);
        Double distance = 2821.86;

        assertEquals(distance, result.getDistance());
        assertEquals(brasilCountryCode, result.getIsoCode());
        assertEquals(iptoTrace, result.getIp());
        assertEquals("Portuguese", result.getLanguage());
        assertEquals("BRL (1 EUR = 6.041236 BRL)", result.getCurrency());
    }
}