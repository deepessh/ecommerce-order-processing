package com.dc297.ecommerce.addressservice.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @Column
    public String line1;

    @Column
    public String line2;

    @Column
    public String city;

    @Column
    public String state;

    @Column
    public String zip;
}
