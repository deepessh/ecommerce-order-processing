package com.dc297.ecommerce.apigateway.controllers;

import com.dc297.ecommerce.apigateway.clients.PaymentMethodClient;
import com.dc297.ecommerce.apigateway.dtos.PaymentMethod;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
class PaymentMethodController {
    public final PaymentMethodClient paymentMethodClient;


    PaymentMethodController(PaymentMethodClient paymentMethodClient) {
        this.paymentMethodClient = paymentMethodClient;
    }

    @GetMapping("/payment-methods")
    @CrossOrigin
    public List<PaymentMethod> read(){
        return paymentMethodClient.read();
    }

    @PostMapping("/payment-methods")
    @CrossOrigin
    public PaymentMethod create(@RequestBody PaymentMethod paymentMethod){
        return paymentMethodClient.create(paymentMethod);
    }
}
