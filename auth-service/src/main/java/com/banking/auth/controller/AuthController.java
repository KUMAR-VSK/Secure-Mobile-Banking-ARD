package com.banking.auth.controller;

import com.banking.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        String email = request.get("email");
        String result = authService.register(username, password, email);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        String token = authService.login(username, password);
        if (token.equals("Invalid credentials")) {
            return ResponseEntity.status(401).body(token);
        }
        return ResponseEntity.ok(token);
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String token) {
        String jwt = token.replace("Bearer ", "");
        return ResponseEntity.ok(new com.banking.auth.security.JwtUtil().validateToken(jwt));
    }

    @GetMapping("/userid")
    public ResponseEntity<Long> getUserId(@RequestParam String username) {
        Long userId = authService.getUserId(username);
        return ResponseEntity.ok(userId);
    }
}
