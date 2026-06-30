package com.chjdev.urlshortener.service;

import com.chjdev.urlshortener.dto.RegisterUserRequest;
import com.chjdev.urlshortener.dto.RegisterUserResponse;
import com.chjdev.urlshortener.entity.UserEntity;
import com.chjdev.urlshortener.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthService {


     private final UserRepository userRepository;
     private  final PasswordEncoder passwordEncoder;


    public AuthService(UserRepository userRepository,  PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
