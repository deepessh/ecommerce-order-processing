package com.dc297.ecommerce.orderService.repositories;

import com.dc297.ecommerce.entities.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository  extends CrudRepository<Customer, UUID> {
    List<Customer> findAll();
}
