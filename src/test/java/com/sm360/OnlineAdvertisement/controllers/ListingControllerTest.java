package com.sm360.OnlineAdvertisement.controllers;

import com.sm360.OnlineAdvertisement.dto.ListingDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ListingControllerTest {
    @Autowired
    TestRestTemplate restTemplate;


    @Test
    void shouldFindAllListings() {
        ResponseEntity<List<ListingDto>> exchange = restTemplate.exchange("/api/v1/listings", HttpMethod.GET, null, new ParameterizedTypeReference<List<ListingDto>>() {});
        assertNotNull(exchange);
        List<ListingDto> listings = exchange.getBody();
        assertTrue(listings.size() > 0);

    }

}
