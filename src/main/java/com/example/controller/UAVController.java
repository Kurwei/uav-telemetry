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
        // Gelen JSON verisi (@RequestBody sayesinde) otomatik olarak UAV objesine dönüşür.
        // Repository kullanarak bu objeyi veritabanına kaydediyoruz.
        UAV savedUAV = uavRepository.save(uav);
        
        // HTTP 200 OK durumuyla beraber veritabanına kaydedilen (ID ve API Key almış) halini geri dönüyoruz.
        return ResponseEntity.ok(savedUAV);
    }

    // GET http://localhost:8080/api/v1/uavs adresine istek atıldığında bu metod çalışır
    @GetMapping
    public ResponseEntity<List<UAV>> getAllUAVs() {
        // Veritabanındaki tüm İHA'ları bulup JSON dizisi olarak geri döner
        return ResponseEntity.ok(uavRepository.findAll());
    }
}