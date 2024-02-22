package com.axmayn.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {

    List<Customer> findAllCustomer();

    Optional<Customer> findCustomerById(Integer id);

    void insertCustomer(Customer customer);

    boolean existByEmail(String email);

    boolean existById(Integer id);

    void deleteCustomer(Integer id);

    Customer findByEmail(String email);
}
