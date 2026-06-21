package dev.lauchy.skyplanner.dto;

import jakarta.validation.constraints.*;

public record RegisterRequest(
    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    String email,

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    String password
) {
    
}
