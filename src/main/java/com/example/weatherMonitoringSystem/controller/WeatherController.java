package com.example.weatherMonitoringSystem.controller;

import com.example.weatherMonitoringSystem.model.WeatherModel;
import com.example.weatherMonitoringSystem.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/weather")
@RequiredArgsConstructor
public class WeatherController {


    Logger logger = LoggerFactory.getLogger(WeatherController.class);
    private final WeatherService weatherService;

    @PostMapping("/fetch")
    public ResponseEntity<String> fetchDataFromAPI(){
        logger.info("use endpoint get user");
        String message = weatherService.fetchData();
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
    @GetMapping("/data")
    public ResponseEntity<List<WeatherModel>> getData(){
        logger.info("use endpoint get user");
        return ResponseEntity.status(HttpStatus.OK).body(weatherService.getAllData());
    }
    @GetMapping("/add")
    public ResponseEntity<String> addDataFromAPI(){
        logger.info("use endpoint get user");
        return ResponseEntity.status(HttpStatus.OK).body(weatherService.fetchAndSaveWeatherData());
    }
}
