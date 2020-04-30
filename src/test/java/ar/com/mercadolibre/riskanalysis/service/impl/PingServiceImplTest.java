package ar.com.mercadolibre.riskanalysis.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
class PingServiceImplTest {

    @InjectMocks
    PingServiceImpl pingService;

    @BeforeEach
    public void beforeAll() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void when_ping_is_invoked() {

        String result = this.pingService.ping();

        assertNull(result);
    }

}
