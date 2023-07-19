package com.example.SpringServer.service;

import com.example.SpringServer.models.Link;
import com.example.SpringServer.repository.LinkRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class LinkService {
    private static final String BASE_URL = "http://localhost:7070/";
    private final LinkRepository linkRepository;

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public Link createShortUrl(String originalUrl) {
        String shortUrl = generateShortUrl();
        if (linkRepository.findByShortUrl(shortUrl)!=null) {
            shortUrl = generateShortUrl();
        }

        Link link = new Link();
        link.setOriginalUrl(originalUrl);
        link.setShortUrl(shortUrl);

        return linkRepository.save(link);
    }

    private String generateShortUrl() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int length = 8;
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return BASE_URL + sb;
    }

    public ResponseEntity<?> getOriginalUrl(String shortUrl) {
        Link link = linkRepository.findByShortUrl(BASE_URL + shortUrl);
        Map<String, Object> response = new HashMap<>();
        if (link == null) {
            response.put("message", "URL not found");
            response.put("status", "failure");
            return ResponseEntity.ok(response);
        }

        if (link.getExpiresAt().isBefore(LocalDateTime.now())) {
            response.put("message", "URL expired");
            response.put("status", "failure");
            return ResponseEntity.ok(response);
        }
        response.put("message", "URL Found");
        response.put("status", "success");
        response.put("link",link.getOriginalUrl());
        return ResponseEntity.ok(response);
    }
}
