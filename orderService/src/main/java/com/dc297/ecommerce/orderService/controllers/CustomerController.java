package com.dc297.ecommerce.orderService.controllers;

import com.dc297.ecommerce.entities.Customer;
import com.dc297.ecommerce.orderService.repositories.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @GetMapping
    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

    @PostMapping
    public Customer create(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }
}
