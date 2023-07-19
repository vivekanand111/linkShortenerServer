package com.example.SpringServer.controler;

import com.example.SpringServer.models.Link;
import com.example.SpringServer.service.LinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/links")
public class LinkController {
    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping
    public ResponseEntity<Link> createLink(@RequestBody Map<String, String> request) {
        String originalUrl = request.get("originalUrl");
        if (originalUrl == null || originalUrl.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Link link = linkService.createShortUrl(originalUrl);
        return ResponseEntity.status(HttpStatus.CREATED).body(link);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String shortUrl) {
        return linkService.getOriginalUrl(shortUrl);
    }
}
