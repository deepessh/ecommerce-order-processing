package com.dc297.ecommerce.apigateway.dtos;

import java.util.Date;
import java.util.UUID;

public class PaymentMethodDto {
    public UUID id;
    public int type;
    public String details;
    public double amount;
    public Date date;
    public String confirmationNumber;
}
