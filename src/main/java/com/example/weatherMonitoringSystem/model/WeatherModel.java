package com.example.weatherMonitoringSystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Table(name = "weather_model")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WeatherModel {
    @Id
    @NotNull
    private UUID id;

    @NotNull
    private String location;

    @NotNull
    private double latitude;

    @NotNull
    private double longitude;

    @NotNull
    private String date;

    @NotNull
    private double degree;
}

