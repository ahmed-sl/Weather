package com.example.weatherMonitoringSystem.repository;

import com.example.weatherMonitoringSystem.model.WeatherModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.UUID;

public interface WeatherRepository extends JpaRepository<WeatherModel, UUID> {
    @Query("SELECT AVG(w.degree) FROM WeatherModel w WHERE w.location = :location")
    Double findAVGDegreeByLocation(String location);
}
