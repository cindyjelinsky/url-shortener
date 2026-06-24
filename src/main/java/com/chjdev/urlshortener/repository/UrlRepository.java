package com.chjdev.urlshortener.repository;

import com.chjdev.urlshortener.entity.Url;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<Url,String> {

}
