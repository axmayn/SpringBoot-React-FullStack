package com.axmayn.customer;

import com.axmayn.exception.DuplicateResourceException;
import com.axmayn.exception.ResourceNotFound;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDao customerDao;


    public CustomerService(@Qualifier("JDBCTempl")CustomerDao customerDao) {
        this.customerDao = customerDao;
    }


    List<Customer> findAllCustomers()
    {
        return customerDao.findAllCustomer();
    };

    Customer findCustomerById(Integer id)
    {
        return customerDao.findCustomerById(id)
                .orElseThrow(() -> new ResourceNotFound("Could not find the id number %s".formatted(id)));
    };

    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest) {

        if(customerDao.existByEmail(customerRegistrationRequest.email())){
            throw new DuplicateResourceException("Email Id alredy exists");

        }

        Customer customer = new Customer(
                customerRegistrationRequest.name(),
                customerRegistrationRequest.email(),
                customerRegistrationRequest.age()
        );

        customerDao.insertCustomer(customer);
    }

    public void removeCustomer(Integer id) {
        if(!customerDao.existById(id)){
            throw new ResourceNotFound("the customer doesn't exist");
        }

        customerDao.deleteCustomer(id);
    }

    @Transactional
    public void updateCustomer(CustomerRegistrationRequest customerUpdateRequest) {
        if(!customerDao.existByEmail(customerUpdateRequest.email())){
            throw new ResourceNotFound("customer with that mail id doesn't exist");
        }

        Customer customer = customerDao.findByEmail(customerUpdateRequest.email());

        customer.setName(customerUpdateRequest.name());
        customer.setAge(customerUpdateRequest.age());

        customerDao.insertCustomer(customer);
    }
}
