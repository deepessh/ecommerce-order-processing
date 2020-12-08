package com.dc297.ecommerce.itemservice.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    public Item item;

    @Column(name = "order_id")
    public UUID orderId;

    @Column(name="quantity")
    public int quantity;
}
