package com.learncicd.customermgmt;

import com.learncicd.customermgmt.domain.Customer;
import com.learncicd.customermgmt.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;


import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *  Use TestRestTemplate for Integration Tests
 * If you're using @SpringBootTest, prefer TestRestTemplate (provided by Spring Boot starter test)
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class CustomerMgmtApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetAllCustomersReturnsDefaultTwo() {
        ResponseEntity<Customer[]> response = restTemplate.getForEntity("/api/customers/list-customers", Customer[].class);
        log.info("Customer List : {}", Arrays.stream(Objects.requireNonNull(response.getBody())).toList());
        assertEquals(200, response.getStatusCode().value());
        assertEquals(3, Objects.requireNonNull(response.getBody()).length);
    }

    @Test
    void testGreetCustomerFound() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/customers/greet?firstName=John", String.class);
        assertEquals(200, response.getStatusCode().value());
        assertTrue(Objects.requireNonNull(response.getBody()).contains("Good Morning, Welcome John Doe"));
        assertTrue(response.getBody().contains("Good Morning, Welcome John Doe"));
    }

    @Test
    void testCustomerNotFound() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/customers/greet?firstName=Ghost", String.class);
        assertEquals(404, response.getStatusCode().value());
        assertTrue(Objects.requireNonNull(response.getBody()).contains("Customer Not FOund With Name"));
        assertTrue(response.getBody().contains("Customer Not FOund With Name"));
    }

    @Test
    void testAddCustomer() {
        Customer customer = new Customer("Elon", "Musk", 50, "1231231234");
        ResponseEntity<String> response = restTemplate.postForEntity("/api/customers/save",customer, String.class);
        assertEquals(200, response.getStatusCode().value());
        assertTrue(Objects.requireNonNull(response.getBody()).contains("Customer added successfully!"));
        assertTrue(response.getBody().contains("Customer added successfully!"));
    }

}
