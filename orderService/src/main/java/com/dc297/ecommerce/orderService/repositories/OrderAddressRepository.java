package com.dc297.ecommerce.orderService.repositories;

import com.dc297.ecommerce.entities.OrderAddress;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface OrderAddressRepository extends CrudRepository<OrderAddress, UUID> {
    List<OrderAddress> findAll();
}
