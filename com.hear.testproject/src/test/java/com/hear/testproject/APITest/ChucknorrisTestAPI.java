package com.hear.testproject.APITest;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class ChucknorrisTestAPI {
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	@Test
	public void chucknorrisCategoriesTest() {

		// request url
		String url = "https://api.chucknorris.io/jokes/categories";

		// create an instance of RestTemplate
		RestTemplate restTemplate = new RestTemplate();

		// create headers
		HttpHeaders headers = new HttpHeaders();

		// set `Content-Type` and `Accept` headers
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(url);

				// example of custom header
		headers.set("X-Request-Source", "Desktop");

		// build the request
		HttpEntity request = new HttpEntity(headers);

		// make an HTTP GET request
		String json = restTemplate.getForObject(url, String.class);
		LOGGER.log(Level.INFO, json);
		// print json

		ResponseEntity<String> response = restTemplate.exchange(urlBuilder.toUriString(), HttpMethod.GET, request, String.class);

		// check response
		if (response.getStatusCode() == HttpStatus.OK) {
			LOGGER.log(Level.INFO, response.getStatusCode().toString());
			System.out.println(response.getBody());
		} else {
			LOGGER.log(Level.INFO, response.getStatusCode().toString());
			System.out.println(response.getStatusCode());
		}

	}

	@Test
	public void chucknorrisSportCategoryTest() {

		// request url
		String url = "https://api.chucknorris.io/jokes/search?query=sport";

		// create an instance of RestTemplate
		RestTemplate restTemplate = new RestTemplate();

		// create headers
		HttpHeaders headers = new HttpHeaders();

		// set `Content-Type` and `Accept` headers
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		// example of custom header
		headers.set("X-Request-Source", "Desktop");

		// build the request
		HttpEntity request = new HttpEntity(headers);

		// make an HTTP GET request
		String json = restTemplate.getForObject(url, String.class);
		LOGGER.log(Level.INFO, json);

		// make an HTTP GET request with headers
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

		// check response
		if (response.getStatusCode() == HttpStatus.OK) {
			LOGGER.log(Level.INFO, response.getStatusCode().toString());
			System.out.println(response.getBody());
		} else {
			LOGGER.log(Level.INFO, response.getStatusCode().toString());
			System.out.println(response.getStatusCode());
		}

	}
}
