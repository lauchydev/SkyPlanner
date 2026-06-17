package dev.lauchy.skyplanner.dto;

public record WeatherResponse(
    String city, 
    String country, 
    double temperature, 
    String description, 
    int humidity, 
    String icon
) {}
