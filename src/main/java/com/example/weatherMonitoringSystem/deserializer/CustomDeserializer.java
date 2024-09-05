package com.example.weatherMonitoringSystem.deserializer;

import com.example.weatherMonitoringSystem.model.WeatherModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CustomDeserializer implements Deserializer<WeatherModel> {
    private ObjectMapper objectMapper = new ObjectMapper();
    Logger logger = LoggerFactory.getLogger(CustomDeserializer.class);
    @Override
    public WeatherModel deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, WeatherModel.class);
        }catch (Exception e){
            logger.error("when custom deserialize WeatherModel with message: " + e.getMessage());
            return null;
        }

    }
}
