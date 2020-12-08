package com.dc297.ecommerce.customerservice.repositories;

import com.dc297.ecommerce.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
