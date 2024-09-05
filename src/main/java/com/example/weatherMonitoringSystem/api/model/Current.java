package com.example.weatherMonitoringSystem.api.model;

import lombok.Data;

@Data
class Current {
    private double feelslikeC;
    private double feelslikeF;
    private long windDegree;
    private double windchillF;
    private double windchillC;
    private long lastUpdatedEpoch;
    private double tempC;
    private double tempF;
    private long cloud;
    private double windKph;
    private double windMph;
    private long humidity;
    private double dewpointF;
    private long uv;
    private String lastUpdated;
    private double heatindexF;
    private double dewpointC;
    private long isDay;
    private long precipIn;
    private double heatindexC;
    private String windDir;
    private long gustMph;
    private double pressureIn;
    private double gustKph;
    private long precipMm;
    private Condition condition;
    private long viskm;
    private long pressuremb;
    private long visMiles;


}
