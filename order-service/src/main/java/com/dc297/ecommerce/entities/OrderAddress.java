package com.dc297.ecommerce.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="order_addresses")
public class OrderAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @OneToOne
    @JoinColumn(name = "order_id")
    public Order order;

    @OneToOne
    @JoinColumn(name = "address_id")
    public Address address;

    @Column
    public int type;
}
