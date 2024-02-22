package com.axmayn.customer;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jpa")
public class CustomerDaoRepositoryService implements CustomerDao{

    private final CustomerReposiory customerReposiory;

    public CustomerDaoRepositoryService(CustomerReposiory customerReposiory) {
        this.customerReposiory = customerReposiory;
    }

    @Override
    public List<Customer> findAllCustomer() {

        return customerReposiory.findAll();
    }


    @Override
    public Optional<Customer> findCustomerById(Integer id) {
        return customerReposiory.findById(id);
    }


    @Override
    public boolean existByEmail(String email) {
        return customerReposiory.existsCustomerByEmail(email);
    }

    @Override
    public boolean existById(Integer id) {
        return customerReposiory.existsCustomerById(id);
    }

    @Override
    public void deleteCustomer(Integer id) {
        customerReposiory.deleteById(id);
    }

    @Override
    public Customer findByEmail(String email) {
        return customerReposiory.findCustomerByEmail(email);
    }


    @Override
    public void insertCustomer(Customer customer) {
        customerReposiory.save(customer);
    }

}
