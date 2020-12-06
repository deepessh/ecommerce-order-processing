package com.dc297.ecommerce.orderService.repositories;

import com.dc297.ecommerce.entities.Customer;
import com.dc297.ecommerce.entities.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends CrudRepository<Item, UUID> {
    List<Item> findAll();
}
