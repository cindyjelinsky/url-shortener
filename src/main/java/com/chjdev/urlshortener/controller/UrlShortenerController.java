package com.chjdev.urlshortener.controller;


import com.chjdev.urlshortener.dto.CreateUrlRequest;
import com.chjdev.urlshortener.dto.CreateUrlResponse;
import com.chjdev.urlshortener.service.UrlShortenerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlShortenerController {

     private final UrlShortenerService urlShortenerService;

     public UrlShortenerController(UrlShortenerService urlShortenerService) {
         this.urlShortenerService = urlShortenerService;
     }

     @RequestMapping("url-shortener")
     @PostMapping
     ResponseEntity<CreateUrlResponse> createUrl(@RequestBody CreateUrlRequest createUrlRequest) {
            CreateUrlResponse response = urlShortenerService.createShortUrl(createUrlRequest);
            return ResponseEntity.ok(response);
     }

     @GetMapping("/{shortCode}")
     ResponseEntity<Void> retrieveUrl(@PathVariable String shortCode) {
         HttpHeaders headers = new HttpHeaders();
         headers.setLocation(URI.create(urlShortenerService.findByShortCode(shortCode)));
         return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
     }




}
