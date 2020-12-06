package com.dc297.ecommerce.orderService.controllers;

import com.dc297.ecommerce.entities.PaymentMethod;
import com.dc297.ecommerce.orderService.repositories.PaymentMethodRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payment-methods")
public class PaymentMethodController {
    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodController(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }


    @GetMapping
    public List<PaymentMethod> getAll(){
        return paymentMethodRepository.findAll();
    }

    @PostMapping
    public PaymentMethod create(@RequestBody PaymentMethod paymentMethod){
        return paymentMethodRepository.save(paymentMethod);
    }
}
