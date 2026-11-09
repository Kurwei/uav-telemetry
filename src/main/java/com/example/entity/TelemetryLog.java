package com.example.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "telemetry_logs")
@Data
public class TelemetryLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne 
    @JoinColumn(name = "uav_id", nullable = false)
    private UAV uav;

    private Double latitude;
    private Double longitude;
    private Double altitude;
    private Double speed;
    private Integer battery;

    private LocalDateTime timestamp;

    @PrePersist
    public void onCreate() {
        if(this.timestamp == null){
            this.timestamp = LocalDateTime.now();
        }
    }
}
