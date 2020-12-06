package com.dc297.ecommerce.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="order_payments")
public class OrderPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @OneToOne
    @JoinColumn(name = "order_id")
    public Order order;

    @OneToOne
    @JoinColumn(name = "method_id")
    public PaymentMethod paymentMethod;

    @Column
    public double amount;

    @Column
    public Date date;

    @Column(name="confirmation_number")
    public String confirmationNumber;
}
