package dev.lauchy.skyplanner.dto;

import java.util.List;

public record OpenWeatherResponse(
    String name,
    Sys sys,
    Main main,
    List<Weather> weather
) {
    public record Sys(String country) {}
    public record Main(double temp, int humidity) {}
    public record Weather(String description, String icon) {}
}