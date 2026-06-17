package dev.lauchy.skyplanner.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import dev.lauchy.skyplanner.dto.WeatherResponse;
import dev.lauchy.skyplanner.exceptions.CityNotFoundException;
import dev.lauchy.skyplanner.exceptions.WeatherServiceException;
import dev.lauchy.skyplanner.dto.OpenWeatherResponse;

@Service
public class WeatherService {
    private final WebClient webClient;
    private final String apiKey;

    public WeatherService(WebClient webClient, @Value("${openweather.api-key}") String apiKey) {
        this.webClient = webClient;
        this.apiKey = apiKey;
    }
    
    // Service method that fetches weather data from the API
    public WeatherResponse getWeatherByCity(String city) {
        
        String apiUrl = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric", city, apiKey);
        
        try {

            // Fetch raw weather
            OpenWeatherResponse raw = webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(OpenWeatherResponse.class)
                .block();

            // Map to custom response
            return new WeatherResponse(
                raw.name(),
                raw.sys().country(),
                raw.main().temp(),
                raw.weather().get(0).description(),
                raw.main().humidity(),
                raw.weather().get(0).icon()
            );
        } catch (WebClientResponseException.NotFound ex) {
            throw new CityNotFoundException("City not found: " + city);
        } catch (WebClientResponseException ex) {
            throw new WeatherServiceException("Weather service unavailable", ex);
        }
        
    }
}
