package com.dc297.ecommerce.apigateway.controllers;

import com.dc297.ecommerce.apigateway.bulk.Sender;
import com.dc297.ecommerce.apigateway.dtos.OrderDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders-bulk")
public class OrderBulkController {

    private final Sender kafkaSender;

    public OrderBulkController(Sender kafkaSender) {
        this.kafkaSender = kafkaSender;
    }

    @PostMapping
    public void create(@RequestBody OrderDto order) { kafkaSender.send(order); }
}
