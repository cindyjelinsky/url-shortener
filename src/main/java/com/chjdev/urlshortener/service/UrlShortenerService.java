package com.chjdev.urlshortener.service;

import com.chjdev.urlshortener.dto.CreateUrlRequest;
import com.chjdev.urlshortener.dto.CreateUrlResponse;
import com.chjdev.urlshortener.entity.UrlEntity;
import com.chjdev.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class UrlShortenerService {

    private static final String CHARACTERS =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private final UrlRepository urlRepository;

    public UrlShortenerService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }




   public CreateUrlResponse createShortUrl(CreateUrlRequest createUrlRequest) {

        String shortCode = generateUniqueCode();

        String url = createUrlRequest.getUrl();
       if (!url.startsWith("http://") && !url.startsWith("https://")) {
           url = "https://" + url;
       }
       UrlEntity urlEntity = new UrlEntity();
       urlEntity.setUrlOriginal(url);
       urlEntity.setCreatedAt(Instant.now());
       urlEntity.setId(shortCode);


       urlRepository.save(urlEntity);

       String shortUrl = "http://localhost:8080/" + shortCode;

       return  new CreateUrlResponse(shortUrl);

   }

   public String findByShortCode(String shortCode) {

        return urlRepository.findById(shortCode)
                .orElseThrow(()-> new RuntimeException("URL Not Found"))
                .getUrlOriginal();

   }


    private String generateUniqueCode(){

        String code ="";

        do {
           code = generateCode();
        } while (urlRepository.existsById(code));

         return code;
    }




   private String generateCode(){

        StringBuilder sb = new StringBuilder();

        for(int i=0;i<6;i++){

            int randomIndex = (int) (Math.random() * CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }

        return sb.toString();
   }


}


