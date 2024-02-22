package com.axmayn.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerReposiory extends JpaRepository<Customer, Integer> {

    boolean existsCustomerByEmail(String email);

    boolean existsCustomerById(Integer id);

    Customer findCustomerByEmail(String Email);

}
