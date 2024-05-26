package com.example.redirectlink.controllers;

import com.example.redirectlink.database.enities.LinkEnity;
import com.example.redirectlink.database.repositories.LinkRepository;
import com.example.redirectlink.models.LinkKey;
import com.example.redirectlink.models.PostResponse;
import com.example.redirectlink.services.BannedWordsCheckService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
//import org.h2.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import com.example.redirectlink.services.IdBaseConverter;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
public class LinkController {
    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private BannedWordsCheckService bannedWordsCheckService;

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

//    public LinkController(LinkRepository linkRepository) {
//        this.linkRepository = linkRepository;
//    }

    @PostMapping("/link")
    @RateLimiter(name = "rateLimiterApi")
    public ResponseEntity addTask(
            @Valid @RequestBody LinkEnity requestData
    ) {

        requestData.setLinkId(UUID.randomUUID());
        requestData.setBase64Id(requestData.getLinkId().toString());

        bannedWordsCheckService.checkIfUrlIncludesBannedWord(requestData.getLink());

        requestData.setInitDate(LocalDate.now());
        LinkEnity savedValue = linkRepository.insert(requestData);

        if(savedValue.equals(requestData)){

            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(savedValue);
        };

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body("Internal server error");
    }
}


