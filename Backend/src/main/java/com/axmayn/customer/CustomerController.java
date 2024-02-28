package com.axmayn.customer;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/")
    public List<Customer> customersList(){
        return customerService.findAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer findCustomerById(@PathVariable("id") Integer id)
    {
        return customerService.findCustomerById(id);
    }

    @PostMapping("/")
    public void registerNewCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest)
    {
        customerService.addCustomer(customerRegistrationRequest);
    }
import org.springframework.web.bind.annotation.*;
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") Integer id){
        customerService.removeCustomer(id);
    }

    @PutMapping("/")
    public void updateCustomer(@RequestBody CustomerRegistrationRequest customerUpdateRequest)
    {
        customerService.updateCustomer(customerUpdateRequest);
    }
}
