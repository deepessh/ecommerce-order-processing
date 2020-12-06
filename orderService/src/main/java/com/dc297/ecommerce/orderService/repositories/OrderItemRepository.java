package com.dc297.ecommerce.orderService.repositories;

import com.dc297.ecommerce.entities.OrderItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface OrderItemRepository extends CrudRepository<OrderItem, UUID> {
    List<OrderItem> findAll();
}
