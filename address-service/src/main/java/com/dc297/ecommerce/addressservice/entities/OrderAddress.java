package com.dc297.ecommerce.addressservice.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="order_addresses")
public class OrderAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @Column(name="order_id")
    public UUID orderId;

    @OneToOne
    @JoinColumn(name = "address_id")
    public Address address;

    @Column
    public int type;
}
