package com.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.UAV;
import com.example.repository.UAVRepository;

@RestController 
@RequestMapping("/api/v1/uavs")
public class UAVController {

    private final UAVRepository uavRepository;

    public UAVController(UAVRepository uavRepository) {
        this.uavRepository = uavRepository;
    }

    @PostMapping
    public ResponseEntity<UAV> registerUAV(@RequestBody UAV uav) {
        
        UAV savedUAV = uavRepository.save(uav);
        
        return ResponseEntity.ok(savedUAV);
    }
    
    @GetMapping
    public ResponseEntity<List<UAV>> getAllUAVs() {
        return ResponseEntity.ok(uavRepository.findAll());
    }
}
