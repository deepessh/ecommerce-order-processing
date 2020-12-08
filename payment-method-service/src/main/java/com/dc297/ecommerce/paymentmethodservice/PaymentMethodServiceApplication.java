package com.dc297.ecommerce.paymentmethodservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMethodServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentMethodServiceApplication.class, args);
	}

}
