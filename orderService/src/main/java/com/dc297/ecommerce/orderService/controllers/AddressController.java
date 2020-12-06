package com.dc297.ecommerce.orderService.controllers;

import com.dc297.ecommerce.entities.Address;
import com.dc297.ecommerce.orderService.repositories.AddressRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {
    private final AddressRepository addressRepository;

    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    @GetMapping
    public List<Address> getAll(){
        return addressRepository.findAll();
    }

    @PostMapping
    public Address create(@RequestBody Address address){
        return addressRepository.save(address);
    }
}
