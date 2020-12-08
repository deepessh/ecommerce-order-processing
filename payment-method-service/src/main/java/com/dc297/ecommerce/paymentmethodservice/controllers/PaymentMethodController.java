package com.dc297.ecommerce.paymentmethodservice.controllers;

import com.dc297.ecommerce.paymentmethodservice.dtos.PaymentMethodDto;
import com.dc297.ecommerce.paymentmethodservice.entities.PaymentMethod;
import com.dc297.ecommerce.paymentmethodservice.repositories.PaymentMethodRepository;
import com.dc297.ecommerce.paymentmethodservice.services.PaymentMethodService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
public class PaymentMethodController {
    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodRepository paymentMethodRepository, PaymentMethodService paymentMethodService) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.paymentMethodService = paymentMethodService;
    }

    @GetMapping("/payment-methods")
    public List<PaymentMethod> getAll(){
        return paymentMethodRepository.findAll();
    }

    @PostMapping("/payment-methods")
    public PaymentMethod create(@RequestBody PaymentMethod paymentMethod){
        return paymentMethodRepository.save(paymentMethod);
    }

    @PutMapping("orders/{orderId}/payment-methods")
    public PaymentMethodDto addPaymentMethodToOrder(@RequestBody PaymentMethodDto paymentMethod, @PathVariable UUID orderId){
        return paymentMethodService.addPaymentMethodToOrder(paymentMethod, orderId);
    }

    @GetMapping("orders/{orderId}/payment-methods")
    public List<PaymentMethodDto> getItemsForOrder(@PathVariable UUID orderId){
        return paymentMethodService.getPaymentMethodsForOrder(orderId);
    }
}
