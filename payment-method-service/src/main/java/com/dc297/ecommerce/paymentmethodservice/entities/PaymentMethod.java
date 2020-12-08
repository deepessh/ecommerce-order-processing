package com.dc297.ecommerce.paymentmethodservice.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="payment_method")
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @Column
    public int type;

    @Column
    public String details;
}
