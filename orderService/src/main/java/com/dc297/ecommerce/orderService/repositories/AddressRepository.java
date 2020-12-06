package com.dc297.ecommerce.orderService.repositories;

import com.dc297.ecommerce.entities.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface AddressRepository  extends CrudRepository<Address, UUID> {
    List<Address> findAll();
}
