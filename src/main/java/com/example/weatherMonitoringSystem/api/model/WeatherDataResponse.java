// WeatherData.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.example.weatherMonitoringSystem.api.model;
import com.example.weatherMonitoringSystem.model.WeatherModel;
import lombok.Data;

import java.util.UUID;

@Data
public class WeatherDataResponse {
    private Current current;
    private Location location;

    public WeatherModel watherDataToWeatherModel(){


        return new WeatherModel(
                UUID.randomUUID(),
                location.getName(),
                location.getLat(),
                location.getLon(),
                location.getLocaltime(),
                current.getTempC()
        );
    }
}

