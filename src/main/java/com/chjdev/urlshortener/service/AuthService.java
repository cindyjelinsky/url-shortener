package com.chjdev.urlshortener.service;

import com.chjdev.urlshortener.dto.LoginRequest;
import com.chjdev.urlshortener.dto.LoginResponse;
import com.chjdev.urlshortener.dto.RegisterUserRequest;
import com.chjdev.urlshortener.dto.RegisterUserResponse;
import com.chjdev.urlshortener.entity.UserEntity;
import com.chjdev.urlshortener.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class AuthService {


     private final UserRepository userRepository;
     private  final PasswordEncoder passwordEncoder;
     private final JwtService jwtService;


    public AuthService(UserRepository userRepository,  PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login (LoginRequest loginRequest) {

        UserEntity user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid Credentials"));

        boolean passwordMatches = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());

        if(!passwordMatches){
            throw new RuntimeException("Invalid credentials");
        }


        return new LoginResponse(jwtService.generateToken(user));
    }


    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) {

        if(userRepository.existsByEmail(registerUserRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

       UserEntity user= new UserEntity();
       user.setEmail(registerUserRequest.getEmail());
       user.setPassword(
               passwordEncoder.encode(registerUserRequest.getPassword())
       );
       user.setName(registerUserRequest.getName());
       user.setCreatedAt(Instant.now());

       userRepository.save(user);


      return  new RegisterUserResponse(user.getEmail(),user.getName());

    }
}
