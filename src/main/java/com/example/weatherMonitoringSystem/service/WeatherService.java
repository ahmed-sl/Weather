package com.example.weatherMonitoringSystem.service;

import com.example.weatherMonitoringSystem.model.WeatherModel;
import com.example.weatherMonitoringSystem.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {
    Logger logger = LoggerFactory.getLogger(WeatherService.class);
    private final WeatherRepository weatherRepository;

    private final List<String> allowedCities = Arrays.asList(
            "riyadh", "jeddah", "mecca", "medina", "dammam",
            "khobar", "abha", "tabuk", "taif", "hail");

    public List<WeatherModel> getAllData() {
        logger.trace("get all data from database");
        return weatherRepository.findAll();
    }

    public String getAVG(String city) {
        String cityLowerCase = city.trim().toLowerCase();
        if (!allowedCities.contains(cityLowerCase)) {
            logger.trace("there is no data for " + cityLowerCase);
            return cityLowerCase + " not in our database";
        }else{
            String location = city.substring(0, 1).toUpperCase() + city.substring(1).toLowerCase();
            double degree_avg = weatherRepository.findAVGDegreeByLocation(location);
            logger.trace("get avg for " + location);
            return location + " average temperature = " + degree_avg;
        }
    }

    public void addWeatherModels(WeatherModel message) {
        logger.trace("save weather model into database");
        weatherRepository.save(message);
    }
}
