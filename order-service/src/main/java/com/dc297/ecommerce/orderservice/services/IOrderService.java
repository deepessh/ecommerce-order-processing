package com.dc297.ecommerce.orderservice.services;

import com.dc297.ecommerce.orderservice.dtos.OrderDto;

import java.util.UUID;

public interface IOrderService {
    OrderDto create(OrderDto order);
    OrderDto getById(UUID id);
    void cancel(UUID id);
}
