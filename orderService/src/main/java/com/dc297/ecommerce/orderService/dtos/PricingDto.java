package com.dc297.ecommerce.orderService.dtos;
import java.util.UUID;

public class PricingDto {
    public UUID id;
    public double subtotal;
    public double tax;
    public double shippingCharges;
    public double total;
}
