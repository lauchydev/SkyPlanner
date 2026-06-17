package dev.lauchy.skyplanner.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Enable Spring Security
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF protection
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Session is stateless
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/weather/**", "/api/weather/", "/api/weather", "/actuator/health").permitAll() // Allow public access to these endpoints
                .anyRequest().authenticated() // All other endpoints require authentication
            );

        return http.build();
    }
}