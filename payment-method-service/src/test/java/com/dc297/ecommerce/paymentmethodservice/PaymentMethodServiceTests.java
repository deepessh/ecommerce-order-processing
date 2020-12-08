package com.dc297.ecommerce.paymentmethodservice;

import com.dc297.ecommerce.paymentmethodservice.dtos.PaymentMethodDto;
import com.dc297.ecommerce.paymentmethodservice.entities.OrderPayment;
import com.dc297.ecommerce.paymentmethodservice.entities.PaymentMethod;
import com.dc297.ecommerce.paymentmethodservice.exceptions.NotFoundException;
import com.dc297.ecommerce.paymentmethodservice.repositories.OrderPaymentRepository;
import com.dc297.ecommerce.paymentmethodservice.repositories.PaymentMethodRepository;
import com.dc297.ecommerce.paymentmethodservice.services.PaymentMethodService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class PaymentMethodServiceTests {

    private final PaymentMethodService paymentMethodService;

    private final PaymentMethod paymentMethod = new PaymentMethod();
    private final OrderPayment orderPayment = new OrderPayment();

    private final UUID testOrderId = UUID.randomUUID();
    private final UUID testPaymentMethodId = UUID.randomUUID();

    private final double testAmount = 10.0;

    private final String testConfirmationNumber = "test_confirmation_number";
    private final Date testDate = new Date();
    private final String testDetails = "test payment method";
    private final int testType = 0;

    private final PaymentMethodRepository paymentMethodRepository;
    private final OrderPaymentRepository orderPaymentRepository;

    public PaymentMethodServiceTests(){
        paymentMethodRepository = Mockito.mock(PaymentMethodRepository.class);
        orderPaymentRepository = Mockito.mock(OrderPaymentRepository.class);

        paymentMethod.id = testPaymentMethodId;
        paymentMethod.details = testDetails;
        paymentMethod.type = testType;
        Mockito.when(paymentMethodRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(paymentMethod));

        orderPayment.paymentMethod = paymentMethod;
        orderPayment.orderId = testOrderId;
        orderPayment.amount = testAmount;
        orderPayment.confirmationNumber = testConfirmationNumber;
        orderPayment.date = testDate;

        Mockito.when(orderPaymentRepository.save(Mockito.any(OrderPayment.class))).thenReturn(orderPayment);

        paymentMethodService = new PaymentMethodService(paymentMethodRepository, orderPaymentRepository);
    }


    @Test
    public void addPaymentMethodToOrder_Should_Return_Success_Given_Success_From_Dependencies(){
        PaymentMethodDto paymentMethodDto = new PaymentMethodDto();
        paymentMethodDto.id = testPaymentMethodId;
        paymentMethodDto.amount = testAmount;
        paymentMethodDto.date = testDate;
        paymentMethodDto.details = testDetails;
        paymentMethodDto.confirmationNumber = testConfirmationNumber;
        paymentMethodDto.type = testType;
        PaymentMethodDto outputPaymentMethodDto = paymentMethodService.addPaymentMethodToOrder(paymentMethodDto, testOrderId);

        checkPaymentMethod(outputPaymentMethodDto);
    }

    private void checkPaymentMethod(PaymentMethodDto outputPaymentMethodDto) {
        assertThat(outputPaymentMethodDto).isNotNull();
        assertThat(outputPaymentMethodDto.id).isEqualTo(testPaymentMethodId);
        assertThat(outputPaymentMethodDto.amount).isEqualTo(testAmount);
        assertThat(outputPaymentMethodDto.details).isEqualTo(testDetails);
        assertThat(outputPaymentMethodDto.confirmationNumber).isEqualTo(testConfirmationNumber);
        assertThat(outputPaymentMethodDto.date).isEqualTo(testDate);
        assertThat(outputPaymentMethodDto.type).isEqualTo(testType);
    }

    @Test
    public void addPaymentMethodToOrder_Should_ThrowNotFoundException_Given_InvalidPaymentMethod(){
        PaymentMethodDto paymentMethodDto = new PaymentMethodDto();
        assertThrows(NotFoundException.class, () -> paymentMethodService.addPaymentMethodToOrder(paymentMethodDto, testOrderId));
    }

    @Test
    public void getPaymentMethodsForOrder_Returns_Success_Given_Success_Response_From_Dependencies(){
        Mockito.when(orderPaymentRepository.findAllByOrderId(Mockito.any(UUID.class))).thenReturn(new ArrayList<>(Arrays.asList(orderPayment, orderPayment)));
        List<PaymentMethodDto> paymentMethods = paymentMethodService.getPaymentMethodsForOrder(testOrderId);

        assertThat(paymentMethods).isNotNull();
        assertThat(paymentMethods.size()).isEqualTo(2);
        paymentMethods.forEach(this::checkPaymentMethod);
    }
}
