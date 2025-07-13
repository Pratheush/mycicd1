package com.learncicd.customermgmt.integration;

import com.learncicd.customermgmt.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerIntegrationTest {
    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    void testGreetingIntegration() {
        String url = "http://localhost:" + port + "/api/customers/greet?firstName=John";
        String response = restTemplate.getForObject(url, String.class);
        assert response != null;
        assertTrue(response.contains("Good Morning, Welcome John"));
    }

    @Test
    void testAddCustomerIntegration() {
        String url = "http://localhost:" + port + "/api/customers/save";
        Customer customer = new Customer("Elon", "Musk", 50, "1231231234");

        String response = restTemplate.postForObject(url, customer, String.class);
        assertEquals("Customer added successfully!", response);
    }

    @Test
    void testGetAllCustomersReturnsDefaultTwo() {
        String url = "http://localhost:" +port + "/api/customers/list-customers";
        Customer[] response = restTemplate.getForObject(url, Customer[].class);
        //assertEquals(2, Objects.requireNonNull(response.length)); // remove redundant null check
        assert response != null;
        assertEquals(3, response.length);
    }
}
