package com.axmayn.customer;

import com.axmayn.exception.DuplicateResourceException;
import com.axmayn.exception.ResourceNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.relational.core.sql.In;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    private CustomerService underTest;

    @Mock
    private CustomerDao customerDao;

    @BeforeEach
    void setUp(){
        underTest = new CustomerService(customerDao);

    }

    @Test
    void findAllCustomers() {
        underTest.findAllCustomers();

        Mockito.verify(customerDao).findAllCustomer();

    }

    @Test
    void getCustomerbyId() {
        Integer id = 5;
        Customer customer = new Customer(id, "aman", "axmayn@gmail.com", 24, "female");

        when(customerDao.findCustomerById(id)).thenReturn(Optional.of(customer));

        Customer actual = underTest.findCustomerById(id);

        assertThat(actual).isEqualTo(customer);

    }

    @Test
    void willThrowGetCustomerById(){
        Integer id = -1;

        when(customerDao.findCustomerById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.findCustomerById(id))
                .isInstanceOf(ResourceNotFound.class)
                .hasMessage("Could not find the id number %s".formatted(id));

    }

    @Test
    void addCustomer() {
        CustomerRegistrationRequest customerRegistrationRequest =
                new CustomerRegistrationRequest("aman", "axmayn@gm", 16, "female");
         when(customerDao.existByEmail(customerRegistrationRequest.email()))
                 .thenReturn(false);

         underTest.addCustomer(customerRegistrationRequest);

        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);

         Mockito.verify(customerDao).insertCustomer(customerArgumentCaptor.capture());

         Customer capturedCustomer = customerArgumentCaptor.getValue();

         assertThat(capturedCustomer.getName()).isEqualTo(customerRegistrationRequest.name());
         assertThat(capturedCustomer.getEmail()).isEqualTo(customerRegistrationRequest.email());

    }

    @Test
    void willThrowWhenEmailExists_addCustomer()
    {
        String email = "axmayn@gm";
        CustomerRegistrationRequest customerRegistrationRequest =
                new CustomerRegistrationRequest("aman", email, 16, "female");


        when(customerDao.existByEmail(email)).thenReturn(true);

        assertThatThrownBy(() -> underTest.addCustomer(customerRegistrationRequest))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessage("Email Id alredy exists");

        verify(customerDao, never()).insertCustomer(any());
    }

    @Test
    void removeCustomer() {

        Integer id = 5;

        when(customerDao.existById(id)).thenReturn(true);

        underTest.removeCustomer(id);

        verify(customerDao).deleteCustomer(id);
    }

    @Test
    void updateCustomer() {

    }
}