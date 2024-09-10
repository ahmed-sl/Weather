package com.example.weatherMonitoringSystem.controller;

import com.example.weatherMonitoringSystem.model.WeatherModel;
import com.example.weatherMonitoringSystem.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/weather")
@RequiredArgsConstructor
public class WeatherController {
    Logger logger = LoggerFactory.getLogger(WeatherController.class);
    private final WeatherService weatherService;
    @GetMapping("/avg/{city}")
    public ResponseEntity<String> fetchDataFromAPI(@PathVariable String city) {
        logger.info("use endpoint avg temp");
        String message = weatherService.getAVG(city);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("/all/{pageNumber}/{pageSize}")
    public ResponseEntity<Page<WeatherModel>> getData(@PathVariable int pageNumber, @PathVariable int pageSize) {
        logger.trace("use endpoint get all weathers");
        return ResponseEntity.status(HttpStatus.OK).body(weatherService.getAllData(pageNumber,pageSize));
    }

}
