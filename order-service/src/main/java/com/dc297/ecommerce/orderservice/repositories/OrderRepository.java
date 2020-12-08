package com.dc297.ecommerce.orderservice.repositories;

import com.dc297.ecommerce.entities.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<Order, UUID> {
    List<Order> findAll();
}
