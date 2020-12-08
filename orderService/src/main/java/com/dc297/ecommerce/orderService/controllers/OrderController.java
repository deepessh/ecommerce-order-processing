package com.dc297.ecommerce.orderService.controllers;

import com.dc297.ecommerce.orderService.dtos.OrderDto;
import com.dc297.ecommerce.orderService.services.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final IOrderService orderService;
    private final Logger LOGGER = LoggerFactory.getLogger(OrderController.class.getName());

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

//    @GetMapping
//    public List<OrderDto> findAll() {
//        return orderService.findAll();
//    }

    @GetMapping("/{orderId}")
    public OrderDto findById(@PathVariable UUID orderId) {
        LOGGER.info("Received request to fetch order by order id {}", orderId);
        return orderService.getById(orderId);
    }

    @PostMapping
    public OrderDto create(@RequestBody OrderDto order) {
        LOGGER.info("Received request to create order.");
        return orderService.create(order);
    }

//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable UUID id){
//        orderService.deleteById(id);
//    }

    @GetMapping("/{id}/cancel")
    public void cancel(@PathVariable UUID id){
        LOGGER.info("Received request to cancel order");
        orderService.cancel(id);
    }
}
