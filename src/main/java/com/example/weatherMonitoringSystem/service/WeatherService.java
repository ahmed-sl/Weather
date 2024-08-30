package com.example.weatherMonitoringSystem.service;

import com.example.weatherMonitoringSystem.model.WeatherModel;
import com.example.weatherMonitoringSystem.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final WeatherRepository weatherRepository;
    public String saveData() {
        WeatherModel weatherModel = new WeatherModel();
        weatherModel.setLocation("Jeddah");
        weatherModel.setLatitude(24.45);
        weatherModel.setLongitude(24.45);
        weatherModel.setDate(LocalDate.now());
        weatherModel.setDegree(44.1);
        weatherRepository.save(weatherModel);
        return "Data save to database !!";
    }

    public List<WeatherModel> getAllData(){
        return weatherRepository.findAll();
    }
}
