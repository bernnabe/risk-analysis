package ar.com.mercadolibre.riskanalysis.controller;

import ar.com.mercadolibre.riskanalysis.service.PingService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    private final PingService pingService;

    public PingController(PingService pingService) {
        this.pingService = pingService;
    }

    @GetMapping(value = {"/ping"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> ping() {
        final String status = this.pingService.ping();
        return ResponseEntity.ok(status);
    }
}
