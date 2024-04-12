package com.example.redirectlink;

import com.example.redirectlink.database.repositories.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedirectLinkApplication {
	@Autowired public LinkRepository linkRepository;

	public static void main(String[] args) {

		SpringApplication.run(RedirectLinkApplication.class, args);

	}

}
