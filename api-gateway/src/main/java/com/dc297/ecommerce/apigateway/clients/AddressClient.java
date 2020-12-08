package com.dc297.ecommerce.apigateway.clients;

import com.dc297.ecommerce.apigateway.dtos.Address;
import com.dc297.ecommerce.apigateway.dtos.AddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@FeignClient("address-service")
public interface AddressClient {
    @GetMapping("/addresses")
    @CrossOrigin
    List<Address> read();

    @PostMapping("/addresses")
    @CrossOrigin
    Address create(@RequestBody Address address);


    @GetMapping("/orders/{orderId}/addresses")
    @CrossOrigin
    List<AddressDto> getAddressesForOrder(@PathVariable UUID orderId);

    @PutMapping("/orders/{orderId}/addresses")
    @CrossOrigin
    AddressDto addAddressToOrder(@RequestBody AddressDto item, @PathVariable UUID orderId);
}
