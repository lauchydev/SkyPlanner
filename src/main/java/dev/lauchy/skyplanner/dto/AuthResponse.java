package dev.lauchy.skyplanner.dto;

public record AuthResponse(

    String token,
    String email // Optional

) {
    
}
