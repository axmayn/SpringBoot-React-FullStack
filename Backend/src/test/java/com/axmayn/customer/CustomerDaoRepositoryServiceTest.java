package com.axmayn.customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDaoRepositoryServiceTest {

    private CustomerDaoRepositoryService underTest;

    @Mock
    private CustomerReposiory customerReposiory;
    AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CustomerDaoRepositoryService(customerReposiory);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void findAllCustomer() {

        underTest.findAllCustomer();

        Mockito.verify(customerReposiory).findAll();

    }

    @Test
    void findCustomerById() {
        Integer id = 0;
        underTest.findCustomerById(id);

        Mockito.verify(customerReposiory).findById(0);

    }

    @Test
    void existByEmail() {
        String email = "amankumarsharma1122@gmail.com";
        underTest.existByEmail(email);

        Mockito.verify(customerReposiory).existsCustomerByEmail(email);
    }

    @Test
    void existById() {
        Integer id = 0;

        underTest.existById(id);

        Mockito.verify(customerReposiory).existsCustomerById(id);
    }

    @Test
    void deleteCustomer() {
    }

    @Test
    void findByEmail() {
    }

    @Test
    void insertCustomer() {
    }
}