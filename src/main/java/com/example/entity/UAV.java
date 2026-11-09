package com.example.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity 
@Table(name = "uavs")
@Data
public class UAV {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; 

    private String model;

    @Column(nullable = false, unique = true)
    private String apiKey;

    @PrePersist
    public void generateApiKey() {
        if (this.apiKey == null) {
            this.apiKey = UUID.randomUUID().toString();
        }
    }
}