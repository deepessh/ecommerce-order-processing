package com.dc297.ecommerce.apigateway.services;

import com.dc297.ecommerce.apigateway.dtos.OrderDto;

import java.util.UUID;

public interface IOrderService {
    OrderDto create(OrderDto order);
    OrderDto getOrderById(UUID id);
    void cancel(UUID id);
}
