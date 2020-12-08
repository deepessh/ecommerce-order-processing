package com.dc297.ecommerce.addressservice.controllers;

import com.dc297.ecommerce.addressservice.dtos.AddressDto;
import com.dc297.ecommerce.addressservice.entities.Address;
import com.dc297.ecommerce.addressservice.repositories.AddressRepository;
import com.dc297.ecommerce.addressservice.services.IAddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping()
public class AddressController {
    private final AddressRepository addressRepository;
    private final IAddressService addressService;

    public AddressController(AddressRepository addressRepository, IAddressService addressService) {
        this.addressRepository = addressRepository;
        this.addressService = addressService;
    }

    @GetMapping("/addresses")
    public List<Address> getAll(){
        return addressRepository.findAll();
    }

    @PostMapping("/addresses")
    public Address create(@RequestBody Address address){
        return addressRepository.save(address);
    }


    @PutMapping("orders/{orderId}/addresses")
    public AddressDto addAddressToOrder(@RequestBody AddressDto address, @PathVariable UUID orderId){
        return addressService.addAddressToOrder(address, orderId);
    }

    @GetMapping("orders/{orderId}/addresses")
    public List<AddressDto> getAddressesForOrder(@PathVariable UUID orderId){
        return addressService.getAddressForOrder(orderId);
    }
}
