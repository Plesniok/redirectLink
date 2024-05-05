package com.example.redirectlink.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannedWordsCheckService {

    private List<String> bannedWords = List.of("messa2ge");

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public boolean checkIfUrlIncludesBannedWord(String url) {
        for (String bannedWord: bannedWords) {
            if (url.toLowerCase().contains(bannedWord.toLowerCase())) {
                kafkaTemplate.send("banned", String.format("banned word %s found in %s", bannedWord, url));
                return true;
            }
        }

        return false;
    }
}
