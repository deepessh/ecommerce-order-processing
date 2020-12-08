package com.dc297.ecommerce.itemservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dc297.ecommerce.itemservice.entities.Item;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
}
