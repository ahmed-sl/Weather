package com.example.weatherMonitoringSystem.api.model;

import lombok.Data;

@Data
class Location {
    private String localtime;
    private String country;
    private long localtimeEpoch;
    private String name;
    private double lon;
    private String region;
    private double lat;
    private String tzid;

}
