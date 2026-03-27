package com.banking.auth.service;

import com.banking.auth.entity.User;
import com.banking.auth.repository.UserRepository;
import com.banking.auth.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String register(String username, String password, String email) {
        if (userRepository.existsByUsername(username)) {
            return "Username already exists";
        }
        User user = new User(username, passwordEncoder.encode(password), email);
        userRepository.save(user);
        return "User registered successfully";
    }

    public String login(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            return "Invalid credentials";
        }
        User user = userOpt.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "Invalid credentials";
        }
        return jwtUtil.generateToken(username);
    }

    public Long getUserId(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        return userOpt.map(User::getId).orElse(null);
    }
}
