package com.modular.controller;

import com.modular.ErrorResponse;
import com.modular.dto.LoginRequestDto;
import com.modular.dto.RegisterRequestDto;
import com.modular.entity.Admin;
import com.modular.repository.AdminRepository;
import com.modular.service.AdminDetailsService;
import com.modular.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired private AdminRepository adminRepo;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtService jwtService;
    @Autowired private AdminDetailsService adminDetailsService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto req) {
        if (adminRepo.findByEmail(req.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Email already in use", "/auth/register", 401, "INVALID_CREDENTIALS"));
        }

        Admin admin = new Admin();
        admin.setEmail(req.getEmail());
        admin.setPassword(passwordEncoder.encode(req.getPassword()));
        adminRepo.save(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body("Admin registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto req) {
        UserDetails user = adminDetailsService.loadUserByUsername(req.getEmail());

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Invalid credentials", "/auth/login", 401, "INVALID_CREDENTIALS"));
        }

        String token = jwtService.generateToken(user.getUsername(),
                user.getAuthorities().iterator().next().getAuthority());

        return ResponseEntity.ok(Map.of("token", token));
    }
}