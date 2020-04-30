package ar.com.mercadolibre.riskanalysis.controller;

import ar.com.mercadolibre.riskanalysis.model.StatisticItem;
import ar.com.mercadolibre.riskanalysis.model.TraceIpResult;
import ar.com.mercadolibre.riskanalysis.service.StatisticService;
import ar.com.mercadolibre.riskanalysis.service.TraceIpService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TraceIpController {

    private final TraceIpService traceIpService;
    private final StatisticService statisticService;

    public TraceIpController(TraceIpService traceIpService, StatisticService statisticService) {
        this.statisticService = statisticService;
        this.traceIpService = traceIpService;
    }

    @GetMapping(value = {"/traceip/{ip}"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TraceIpResult> getTraceIp(@PathVariable String ip) {
        TraceIpResult traceIpResult = this.traceIpService.getTraceIp(ip);
        StatisticItem statisticItem = new StatisticItem(traceIpResult.getIsoCode(), traceIpResult.getDistance());
        this.statisticService.incrementScore(statisticItem);

        return ResponseEntity.ok(traceIpResult);
    }
}
