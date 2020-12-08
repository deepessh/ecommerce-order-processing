package com.dc297.ecommerce.addressservice.repositories;

import com.dc297.ecommerce.addressservice.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
