package com.dc297.ecommerce.paymentmethodservice.services;

import com.dc297.ecommerce.paymentmethodservice.dtos.PaymentMethodDto;
import com.dc297.ecommerce.paymentmethodservice.entities.OrderPayment;
import com.dc297.ecommerce.paymentmethodservice.exceptions.NotFoundException;
import com.dc297.ecommerce.paymentmethodservice.repositories.OrderPaymentRepository;
import com.dc297.ecommerce.paymentmethodservice.repositories.PaymentMethodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaymentMethodService implements IPaymentMethodService{
    private final PaymentMethodRepository paymentMethodRepository;
    private final OrderPaymentRepository orderPaymentRepository;

    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository, OrderPaymentRepository orderPaymentRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.orderPaymentRepository = orderPaymentRepository;
    }

    @Override
    public PaymentMethodDto addPaymentMethodToOrder(PaymentMethodDto x, UUID orderId) {
        if(x == null || orderId == null) return null;
        OrderPayment orderPayment = new OrderPayment();

        orderPayment.paymentMethod = paymentMethodRepository.findById(x.id).orElseThrow(NotFoundException::new);
        orderPayment.orderId = orderId;
        orderPayment.amount = x.amount;
        orderPayment.confirmationNumber = x.confirmationNumber;
        orderPayment.date = x.date;
        return paymentMethodEntityToDto(orderPaymentRepository.save(orderPayment));
    }

    @Override
    public List<PaymentMethodDto> getPaymentMethodsForOrder(UUID orderId) {
        return orderPaymentRepository.findAllByOrderId(orderId).parallelStream().map(this::paymentMethodEntityToDto).collect(Collectors.toList());
    }

    private PaymentMethodDto paymentMethodEntityToDto(OrderPayment x) {
        if(x == null) return null;
        PaymentMethodDto output = new PaymentMethodDto();

        output.details = x.paymentMethod.details;
        output.id = x.paymentMethod.id;
        output.type = x.paymentMethod.type;
        output.date = x.date;
        output.confirmationNumber = x.confirmationNumber;
        output.amount = x.amount;

        return output;
    }
}
