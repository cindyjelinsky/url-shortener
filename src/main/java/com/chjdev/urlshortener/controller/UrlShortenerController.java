package com.chjdev.urlshortener.controller;


import com.chjdev.urlshortener.dto.CreateUrlRequest;
import com.chjdev.urlshortener.entity.UrlEntity;
import com.chjdev.urlshortener.service.UrlShortenerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("url-shortener")
public class UrlShortenerController {

     private final UrlShortenerService urlShortenerService;

     public UrlShortenerController(UrlShortenerService urlShortenerService) {
         this.urlShortenerService = urlShortenerService;
     }




}
