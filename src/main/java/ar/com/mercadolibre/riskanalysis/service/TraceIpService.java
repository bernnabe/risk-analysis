package ar.com.mercadolibre.riskanalysis.service;

import ar.com.mercadolibre.riskanalysis.model.TraceIpResult;

public interface TraceIpService {
    TraceIpResult getTraceIp(String ip);
}
