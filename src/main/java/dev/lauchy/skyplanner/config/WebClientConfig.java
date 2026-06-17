package dev.lauchy.skyplanner.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuration class for WebClient
 * This class tells Spring how to create and manage WebClient instances.
 * This instance calls OpenWeatherMap API to get weather data.
 */
@Configuration
public class WebClientConfig {
  @Bean
  public WebClient webClient(WebClient.Builder builder) {
    return builder.build();
  }
}
