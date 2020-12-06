package com.dc297.ecommerce.orderService.repositories;

import com.dc297.ecommerce.entities.OrderPayment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface OrderPaymentRepository extends CrudRepository<OrderPayment, UUID> {
    List<OrderPayment> findAll();
}
