package com.example.weatherMonitoringSystem;

import com.example.weatherMonitoringSystem.api.generator.IngestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WeatherMonitoringSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(WeatherMonitoringSystemApplication.class, args);
	}


}
