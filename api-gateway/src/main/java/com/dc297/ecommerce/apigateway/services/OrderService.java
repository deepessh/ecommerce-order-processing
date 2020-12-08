package com.dc297.ecommerce.apigateway.services;

import com.dc297.ecommerce.apigateway.clients.AddressClient;
import com.dc297.ecommerce.apigateway.clients.ItemClient;
import com.dc297.ecommerce.apigateway.clients.OrderClient;
import com.dc297.ecommerce.apigateway.clients.PaymentMethodClient;
import com.dc297.ecommerce.apigateway.dtos.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    private final Logger LOGGER = LoggerFactory.getLogger(OrderService.class.getName());

    private final OrderClient orderClient;
    private final ItemClient itemClient;
    private final AddressClient addressClient;
    private final PaymentMethodClient paymentMethodClient;

    public OrderService(OrderClient orderClient, ItemClient itemClient, AddressClient addressClient, PaymentMethodClient paymentMethodClient) {
        this.orderClient = orderClient;
        this.itemClient = itemClient;
        this.addressClient = addressClient;
        this.paymentMethodClient = paymentMethodClient;
    }

    @Override
    public OrderDto create(OrderDto order) {
        LOGGER.info("Creating order");
        OrderDto savedOrder = orderClient.create(order);
        savedOrder.addresses = order.addresses.parallelStream().map(x -> addAddressToOrder(x, savedOrder.id)).collect(Collectors.toList());
        savedOrder.items = order.items.parallelStream().map(x -> addItemToOrder(x, savedOrder.id)).collect(Collectors.toList());
        savedOrder.paymentMethods = order.paymentMethods.parallelStream().map(x -> addPaymentMethodToOrder(x, savedOrder.id)).collect(Collectors.toList());
        LOGGER.info("Successfully created order");
        return savedOrder;
    }

    @Override
    public OrderDto getOrderById(UUID id) {
        LOGGER.info("Getting order for id {}", id);
        OrderDto order = orderClient.get(id);

        LOGGER.info("Getting order payment methods for id {}", id);
        order.paymentMethods = paymentMethodClient.getPaymentMethodsForOrder(id);

        LOGGER.info("Getting order items for id {}", id);
        order.items = itemClient.getItemsForOrder(id);

        LOGGER.info("Getting order addresses for id {}", id);
        order.addresses = addressClient.getAddressesForOrder(id);

        return order;
    }

    @Override
    public void cancel(UUID id) {
        orderClient.cancel(id);
    }

    @Override
    public void updateStatus(UUID orderId, int status) {
        orderClient.updateStatus(orderId, status);
    }

    private ItemDto addItemToOrder(ItemDto itemDto, UUID orderId){
        return itemClient.addItemToOrder(itemDto, orderId);
    }

    private AddressDto addAddressToOrder(AddressDto addressDto, UUID orderId){
        return addressClient.addAddressToOrder(addressDto, orderId);
    }

    private PaymentMethodDto addPaymentMethodToOrder(PaymentMethodDto paymentMethodDto, UUID orderId){
        return paymentMethodClient.addPaymentMethodToOrder(paymentMethodDto, orderId);
    }
}
