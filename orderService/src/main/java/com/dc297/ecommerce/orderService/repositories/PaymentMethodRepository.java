package com.dc297.ecommerce.orderService.repositories;

import com.dc297.ecommerce.entities.PaymentMethod;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface PaymentMethodRepository  extends CrudRepository<PaymentMethod, UUID> {
    List<PaymentMethod> findAll();
}
