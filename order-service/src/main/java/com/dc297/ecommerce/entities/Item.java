package com.dc297.ecommerce.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @Column
    public String name;

    @Column
    public double price;
}
