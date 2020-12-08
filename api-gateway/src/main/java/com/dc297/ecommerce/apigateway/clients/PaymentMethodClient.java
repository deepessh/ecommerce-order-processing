package com.dc297.ecommerce.apigateway.clients;

import com.dc297.ecommerce.apigateway.dtos.PaymentMethod;
import com.dc297.ecommerce.apigateway.dtos.PaymentMethodDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@FeignClient("payment-method-service")
public interface PaymentMethodClient {
    @GetMapping("/payment-methods")
    @CrossOrigin
    List<PaymentMethod> read();

    @PostMapping("/payment-methods")
    @CrossOrigin
    PaymentMethod create(@RequestBody PaymentMethod paymentMethod);


    @GetMapping("/orders/{orderId}/payment-methods")
    @CrossOrigin
    List<PaymentMethodDto> getPaymentMethodsForOrder(@PathVariable UUID orderId);

    @PutMapping("/orders/{orderId}/payment-methods")
    @CrossOrigin
    PaymentMethodDto addPaymentMethodToOrder(@RequestBody PaymentMethodDto item, @PathVariable UUID orderId);
}
