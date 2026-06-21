package dev.lauchy.skyplanner.service;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import dev.lauchy.skyplanner.dto.WeatherResponse;
import reactor.core.publisher.Mono;

class WeatherServiceTest {
    private WeatherService weatherService;
    @BeforeEach
    void setUp() {
        // Fake JSON that matches your WeatherResponse (OpenWeatherMap) record fields
        // String json = """
        //     {
        //       "city": "London",
        //       "country": "GB",
        //       "temperature": 15.5,
        //       "description": "clear sky",
        //       "humidity": 72,
        //       "icon": "01d"
        //     }
        //     """;

        String json = """
            {
            "name": "London",
            "sys": { "country": "GB" },
            "main": { "temp": 15.5, "humidity": 72 },
            "weather": [
                { "description": "clear sky", "icon": "01d" }
            ]
            }
            """;
        
        WebClient webClient = WebClient.builder()
                .exchangeFunction(request -> Mono.just(
                        ClientResponse.create(HttpStatus.OK)
                                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                .body(json)
                                .build()))
                .build();
        weatherService = new WeatherService(webClient, "test-api-key");
    }
    @Test
    void getWeatherByCity_returnsMappedResponse() {
        WeatherResponse response = weatherService.getWeatherByCity("London");
        System.out.println(response);
        assertNotNull(response);
        assertEquals("London", response.city());
        assertEquals("GB", response.country());
        assertEquals(15.5, response.temperature());
        assertEquals("clear sky", response.description());
        assertEquals(72, response.humidity());
        assertEquals("01d", response.icon());
    }
}