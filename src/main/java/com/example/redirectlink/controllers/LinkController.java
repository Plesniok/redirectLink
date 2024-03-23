package com.example.redirectlink.controllers;

import com.example.redirectlink.database.enities.LinkEnity;
import com.example.redirectlink.database.repositories.LinkRepository;
import com.example.redirectlink.models.PostResponse;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import services.IdBaseConverter;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class LinkController {
    private final LinkRepository linkRepository;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @Autowired
    public LinkController(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @PostMapping("/link")
    @RateLimiter(name = "rateLimiterApi")
    public PostResponse addTask(
            @Valid @RequestBody LinkEnity requestData
    ) throws BadRequestException {
        if( linkRepository.save(requestData).equals(requestData)){



            return new PostResponse(IdBaseConverter.toBase62(requestData.getId()));
        };

        throw new BadRequestException("Internal server error");
    }

    @GetMapping("/link/{id}")
    public ResponseEntity<Object> getLink(
            @PathVariable("id") String encodedLinkId
    ) {
        Long linkId = IdBaseConverter.fromBase62ToLong(encodedLinkId);

        System.out.println(linkId);

         Optional<LinkEnity> foundLink = linkRepository.findById(linkId);

        if (foundLink.isPresent()) {

            URI location = URI.create(foundLink.get().getLink());
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(location);
            return new ResponseEntity<Object>(headers, HttpStatus.FOUND);
        } else {

            return ResponseEntity.notFound().build();
        }
    }
}


