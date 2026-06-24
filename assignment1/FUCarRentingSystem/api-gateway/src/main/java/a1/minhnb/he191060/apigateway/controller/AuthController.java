package a1.minhnb.he191060.apigateway.controller;

import a1.minhnb.he191060.apigateway.dto.AuthRequest;
import a1.minhnb.he191060.apigateway.dto.AuthResponse;
import a1.minhnb.he191060.apigateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;

    @Autowired
    private JwtUtil jwtUtil;

    private final WebClient.Builder webClientBuilder;

    public AuthController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest request) {
        // Check Admin
        if (adminEmail.equals(request.getEmail()) && adminPassword.equals(request.getPassword())) {
            String token = jwtUtil.generateToken(request.getEmail(), "ADMIN");
            return Mono.just(ResponseEntity.ok(new AuthResponse(token)));
        }

        // Check Customer via Customer-Service
        return webClientBuilder.build()
                .post()
                .uri("http://CUSTOMER-SERVICE/api/customers/verify")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Boolean.class)
                .map(isValid -> {
                    if (isValid) {
                        String token = jwtUtil.generateToken(request.getEmail(), "CUSTOMER");
                        return ResponseEntity.ok(new AuthResponse(token));
                    } else {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).<AuthResponse>build();
                    }
                })
                .onErrorReturn(ResponseEntity.status(HttpStatus.UNAUTHORIZED).<AuthResponse>build());
    }
}
