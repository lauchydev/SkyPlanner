package dev.lauchy.skyplanner.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.lauchy.skyplanner.dto.AuthResponse;
import dev.lauchy.skyplanner.dto.LoginRequest;
import dev.lauchy.skyplanner.dto.RegisterRequest;
import dev.lauchy.skyplanner.model.User;
import dev.lauchy.skyplanner.repository.UserRepository;

@Service
public class AuthService {

    // Inject UserRepository
    private final UserRepository userRepository;
    private final JwtService jwtService;

    // Constructor injection
    public AuthService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest request) {
        
        // Check email not taken
        if(userRepository.findByEmail(request.email()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already taken");
        }

        // BCrypt hash password
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(request.password());

        // Save `User` entity
        User user = new User();
        user.setEmail(request.email());
        user.setPassword(hashedPassword);
        userRepository.save(user);

        // Return JWT
        String token = jwtService.generateToken(user);
        return new AuthResponse(token, request.email());
    }

    public AuthResponse login(LoginRequest request) {
        // Find by email
        User user = userRepository.findByEmail(request.email()).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                 "Invalid credentials"));

        //passwordencoder.matches 
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        if(!encoder.matches(request.password(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                 "Invalid credentials");
        }

        // Return JWT or return 401
        String token = jwtService.generateToken(user);
        return new AuthResponse(token, request.email());
    }
    
}
