package com.dc297.ecommerce.apigateway.controllers;

import com.dc297.ecommerce.apigateway.dtos.OrderDto;
import com.dc297.ecommerce.apigateway.services.IOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class OrderController {

    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    @CrossOrigin
    public OrderDto create(@RequestBody OrderDto order){
        return orderService.create(order);
    }

    @GetMapping("/orders/{orderId}/cancel")
    @CrossOrigin
    public void cancel(@PathVariable UUID orderId){
        orderService.cancel(orderId);
    }

    @GetMapping("/orders/{orderId}")
    @CrossOrigin
    public OrderDto getOrder(@PathVariable UUID orderId){
        return orderService.getOrderById(orderId);
    }
}
