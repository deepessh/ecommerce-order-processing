package com.dc297.ecommerce.orderService.controllers;

import com.dc297.ecommerce.orderService.dtos.OrderDto;
import com.dc297.ecommerce.orderService.services.IOrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private ObjectMapper objectMapper = new ObjectMapper();
    private final IOrderService orderService;
    Logger logger = LoggerFactory.getLogger(OrderController.class);

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDto> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public OrderDto findById(@PathVariable UUID id) {
        return orderService.getById(id);
    }

    @PostMapping
    public OrderDto create(@RequestBody OrderDto order) {
        logger.info(String.format("Received order creation request. Details: Status: %d Shipping method %d Shipping method notes: %s",
                order.status, order.shippingMethod, order.shippingMethodNotes));
        return orderService.create(order);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        orderService.deleteById(id);
    }

    @GetMapping("/{id}/cancel")
    public void cancel(@PathVariable UUID id){
        orderService.cancel(id);
    }
}
