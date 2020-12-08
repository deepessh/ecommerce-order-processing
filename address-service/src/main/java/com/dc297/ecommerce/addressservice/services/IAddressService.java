package com.dc297.ecommerce.addressservice.services;

import com.dc297.ecommerce.addressservice.dtos.AddressDto;

import java.util.List;
import java.util.UUID;

public interface IAddressService {
    AddressDto addAddressToOrder(AddressDto address, UUID orderId);

    List<AddressDto> getAddressForOrder(UUID orderId);
}
