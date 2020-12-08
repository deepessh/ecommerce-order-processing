package com.dc297.ecommerce.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @Column(name = "status")
    public int status;

    @Column(name = "shipping_method")
    public int shippingMethod;

    @Column(name = "shipping_method_notes")
    public String shippingMethodNotes;

    @ManyToOne
    public Customer customer;

    @Column
    public double subtotal;

    @Column
    public double tax;

    @Column(name="shipping_charges")
    public double shippingCharges;

    @Column
    public double total;
}
