package ar.com.mercadolibre.riskanalysis.service.impl;

import ar.com.mercadolibre.riskanalysis.service.PingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PingServiceImpl implements PingService {

    @Value("${risk-analysis.service.ping-service.status-ok}")
    private String statusOk;

    public PingServiceImpl() {
    }

    public String ping() {

        final String status = this.statusOk;
        return status;
    }
}