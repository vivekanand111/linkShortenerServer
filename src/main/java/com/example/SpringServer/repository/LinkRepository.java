package com.example.SpringServer.repository;

import com.example.SpringServer.models.Link;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends MongoRepository<Link, String> {

    Link findByShortUrl(String shortUrl);
}