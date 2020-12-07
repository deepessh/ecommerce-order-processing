package com.dc297.ecommerce.orderService.controllers;

import com.dc297.ecommerce.orderService.dtos.OrderDto;
import com.dc297.ecommerce.orderService.services.IOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final IOrderService orderService;

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
    public OrderDto create(@RequestBody OrderDto order) { return orderService.create(order); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        orderService.deleteById(id);
    }

    @GetMapping("/{id}/cancel")
    public void cancel(@PathVariable UUID id){ orderService.cancel(id); }
}
