package com.example.demo;

import reactor.core.publisher.Mono;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WebClientTestApplication {

	@PostMapping(value = "/profile/change-password", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public Mono<String> changePasswordSubmit(Authentication authentication, @RequestBody MultiValueMap<String, String> formData) {
		return Mono.just(authentication.getName());
	}

	public static void main(String[] args) {
		SpringApplication.run(WebClientTestApplication.class, args);
	}

}
