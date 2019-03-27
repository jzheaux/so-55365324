package com.example.demo;

import java.util.Collections;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

@RunWith(SpringRunner.class)
@WebFluxTest(controllers = WebClientTestApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Import({ThymeleafAutoConfiguration.class})
@WithMockUser(username = "test", password = "password")
public class WebClientTestApplicationTests {

	@Autowired
	WebTestClient webTestClient;

	@Test

	public void addNewEntrySubmit() {
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.put("password1", Collections.singletonList("password"));
		formData.put("password2", Collections.singletonList("password"));

		byte[] response =
		webTestClient.mutateWith(csrf()).post().uri("/profile/change-password")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.body(BodyInserters.fromFormData(formData))
				.exchange()
				.expectStatus().isOk()
				.expectBody().returnResult().getResponseBody();/*
				.expectHeader().valueEquals(HttpHeaders.LOCATION, "/page/1");*/

		assertThat(new String(response)).isEqualTo("test");
	}
}
