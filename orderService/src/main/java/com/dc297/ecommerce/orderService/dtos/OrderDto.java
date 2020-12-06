package com.dc297.ecommerce.orderService.dtos;

import java.util.List;
import java.util.UUID;

public class OrderDto {
    public UUID id;
    public int status;
    public int shippingMethod;
    public String shippingMethodNotes;
    public CustomerDto customer;
    public List<ItemDto> items;
    public List<AddressDto> addresses;
    public List<PaymentMethodDto> paymentMethods;
    public PricingDto pricing;
}
