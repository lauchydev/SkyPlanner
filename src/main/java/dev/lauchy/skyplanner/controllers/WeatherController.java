package dev.lauchy.skyplanner.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import dev.lauchy.skyplanner.dto.WeatherResponse;
import dev.lauchy.skyplanner.service.WeatherService;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    // Controller method that handles the request
    @GetMapping("/{city}")
    public WeatherResponse getWeather(@PathVariable String city) {
        if(city == null || city.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "City is required");
        }
        return weatherService.getWeatherByCity(city.trim());
    }
}
