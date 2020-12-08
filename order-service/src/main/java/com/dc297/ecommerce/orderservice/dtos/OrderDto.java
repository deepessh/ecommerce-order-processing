package com.dc297.ecommerce.orderservice.dtos;

import java.util.UUID;

public class OrderDto {
    public UUID id;
    public int status;
    public int shippingMethod;
    public double subtotal;
    public double tax;
    public double shippingCharges;
    public double total;
    public String shippingMethodNotes;
    public CustomerDto customer;

}
