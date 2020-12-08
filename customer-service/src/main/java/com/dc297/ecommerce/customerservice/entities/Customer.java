package com.dc297.ecommerce.customerservice.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @Column
    public String name;
}
