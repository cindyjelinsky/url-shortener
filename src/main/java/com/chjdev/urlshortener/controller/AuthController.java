package com.chjdev.urlshortener.controller;


import com.chjdev.urlshortener.dto.LoginRequest;
import com.chjdev.urlshortener.dto.LoginResponse;
import com.chjdev.urlshortener.dto.RegisterUserRequest;
import com.chjdev.urlshortener.dto.RegisterUserResponse;
import com.chjdev.urlshortener.service.AuthService;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody RegisterUserRequest registerUserRequest) {

        RegisterUserResponse response = authService.registerUser(registerUserRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        LoginResponse response = authService.login(loginRequest);

        return ResponseEntity.ok(response);

    }



}
