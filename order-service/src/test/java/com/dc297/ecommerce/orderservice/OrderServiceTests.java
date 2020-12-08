package com.dc297.ecommerce.orderservice;

import com.dc297.ecommerce.entities.Customer;
import com.dc297.ecommerce.entities.Order;
import com.dc297.ecommerce.orderservice.dtos.CustomerDto;
import com.dc297.ecommerce.orderservice.dtos.OrderDto;
import com.dc297.ecommerce.orderservice.exceptions.NotFoundException;
import com.dc297.ecommerce.orderservice.repositories.CustomerRepository;
import com.dc297.ecommerce.orderservice.repositories.OrderRepository;
import com.dc297.ecommerce.orderservice.services.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderServiceTests {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderService orderService;

    private final Order testOrderEntity = new Order();
    private final OrderDto testOrderDto = new OrderDto();
    private final Customer testCustomer = new Customer();

    private final UUID testCustomerId = UUID.randomUUID();
    private final String testCustomerName = "test_customer_name";
    private final UUID testOrderId = UUID.randomUUID();
    private final int testStatus = 0;
    private final int testShippingMethod = 0;
    private final String testShippingMethodNotes = "test_shipping_method_notes";
    private final double testSubtotal = 10.0;
    private final double testTax = 0.825;
    private final double testShippingCharges = 5.0;
    private final double testTotal = 15.825;


    public OrderServiceTests(){
        orderRepository = Mockito.mock(OrderRepository.class);
        customerRepository = Mockito.mock(CustomerRepository.class);

        setupData();

        orderService = new OrderService(orderRepository, customerRepository);
    }

    private void setupData() {
        testOrderDto.customer = new CustomerDto();
        testOrderDto.customer.id = testCustomerId;
        testOrderDto.shippingCharges = testShippingCharges;
        testOrderDto.tax = testTax;
        testOrderDto.total = testTotal;
        testOrderDto.shippingMethod = testShippingMethod;
        testOrderDto.shippingMethodNotes = testShippingMethodNotes;
        testOrderDto.status = testStatus;
        testOrderDto.subtotal = testSubtotal;

        testCustomer.id = testCustomerId;
        testCustomer.name = testCustomerName;

        testOrderEntity.id = testOrderId;
        testOrderEntity.customer = testCustomer;
        testOrderEntity.shippingCharges = testShippingCharges;
        testOrderEntity.tax = testTax;
        testOrderEntity.total = testTotal;
        testOrderEntity.shippingMethod = testShippingMethod;
        testOrderEntity.shippingMethodNotes = testShippingMethodNotes;
        testOrderEntity.status = testStatus;
        testOrderEntity.subtotal = testSubtotal;
    }

    @Test
    public void create_Should_Return_Success_Given_Success_From_Repository(){
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(testOrderEntity);
        Mockito.when(customerRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(testCustomer));

        OrderDto savedOrder = orderService.create(testOrderDto);

        checkOrder(savedOrder);
    }

    @Test
    public void getById_Should_Return_Order_Given_Success_From_Repository(){
        Mockito.when((orderRepository.findById(Mockito.any(UUID.class)))).thenReturn(Optional.of(testOrderEntity));

        OrderDto order = orderService.getById(testOrderId);

        checkOrder(order);
    }

    @Test
    public void getById_Should_Throw_Exception_Given_Error_From_Repository(){
        Mockito.when((orderRepository.findById(Mockito.any(UUID.class)))).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> orderService.getById(testOrderId));
    }

    private void checkOrder(OrderDto order){
        assertThat(order).isNotNull();
        assertThat(order.id).isEqualTo(testOrderId);
        assertThat(order.customer).isNotNull();
        assertThat(order.customer.id).isEqualTo(testCustomerId);
        assertThat(order.customer.name).isEqualTo(testCustomerName);
        assertThat(order.shippingCharges).isEqualTo(testShippingCharges);
        assertThat(order.shippingMethod).isEqualTo(testShippingMethod);
        assertThat(order.tax).isEqualTo(testTax);
        assertThat(order.subtotal).isEqualTo(testSubtotal);
        assertThat(order.total).isEqualTo(testTotal);
        assertThat(order.shippingMethodNotes).isEqualTo(testShippingMethodNotes);
        assertThat(order.status).isEqualTo(testStatus);

    }
}
