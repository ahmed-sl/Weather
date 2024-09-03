package com.example.weatherMonitoringSystem.repository;

import com.example.weatherMonitoringSystem.model.WeatherModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeatherRepository extends JpaRepository<WeatherModel,String> {

    WeatherModel findAllByLocation(String location);
}
