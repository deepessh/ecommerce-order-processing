package com.dc297.ecommerce.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="order_pricing")
public class OrderPricing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @Column
    public double subtotal;

    @Column
    public double tax;

    @Column(name="shipping_charges")
    public double shippingCharges;

    @Column
    public double total;
}
