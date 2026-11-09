package com.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.TelemetryLog;
import com.example.entity.UAV;
import com.example.repository.TelemetryLogRepository;
import com.example.repository.UAVRepository;

@RestController 
@RequestMapping("/api/v1/telemetry")
public class TelemetryLogController {
    
    private final TelemetryLogRepository telemetryLogRepository;
    private final UAVRepository uavRepository;

    public TelemetryLogController(TelemetryLogRepository telemetryLogRepository, UAVRepository uavRepository) {
        this.telemetryLogRepository = telemetryLogRepository;
        this.uavRepository = uavRepository;
    }

    @PostMapping
    public ResponseEntity<?> saveTelemetry(@RequestHeader("X-API-KEY") String apiKey, @RequestBody TelemetryLog telemetryLog) {
        UAV uav = uavRepository.findByApiKey(apiKey);
        if (uav == null) {
            return ResponseEntity.status(401).body("Bad API.");
        }

        telemetryLog.setUav(uav);
        TelemetryLog savedLog = telemetryLogRepository.save(telemetryLog);

        return ResponseEntity.ok(savedLog);
    }

    @GetMapping("/uav/{uavId}")
    public ResponseEntity<List<TelemetryLog>> GetTelemetryByUav(@PathVariable Long uavId) {
        return ResponseEntity.ok(telemetryLogRepository.findByUavIdOrderByTimestampDesc(uavId));
    }
}
