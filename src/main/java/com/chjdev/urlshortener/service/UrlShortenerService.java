package com.chjdev.urlshortener.service;

import com.chjdev.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
public class UrlShortenerService {

    private static final String CHARACTERS =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private final UrlRepository urlRepository;

    public UrlShortenerService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
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


