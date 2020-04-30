package ar.com.mercadolibre.riskanalysis.controller;

import ar.com.mercadolibre.riskanalysis.model.Statistic;
import ar.com.mercadolibre.riskanalysis.service.StatisticService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class StatisticController {
    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping(value = {"/stats"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Statistic> getStats() throws IOException {
        return ResponseEntity.ok(this.statisticService.find());
    }
}
