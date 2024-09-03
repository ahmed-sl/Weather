package com.example.weatherMonitoringSystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class WeatherMonitoringSystemApplicationTests {
	@Test
	public void testFetchWeatherData() {
		RestTemplate restTemplate = new RestTemplate();
		String apiKey = "2753e6dcf6fd4750ab7115505242908";
		String city = "riyadh";
		String url = String.format("https://api.weatherapi.com/v1/current.json?key=%s&q=%s&aqi=no", apiKey, city);

		String response = restTemplate.getForObject(url, String.class);

		assertNotNull(response, "API response should not be null");

		assertTrue(response.contains("\"name\":\"Riyadh\""), "Response should contain the city name 'Riyadh'");
		assertTrue(response.contains("\"country\":\"Saudi Arabia\""), "Response should contain the country name 'Saudi Arabia'");
	}

}
