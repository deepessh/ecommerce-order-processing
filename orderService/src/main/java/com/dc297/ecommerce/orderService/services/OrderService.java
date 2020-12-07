package com.dc297.ecommerce.orderService.services;

import com.dc297.ecommerce.entities.*;
import com.dc297.ecommerce.orderService.dtos.*;
import com.dc297.ecommerce.orderService.exceptions.BadRequestException;
import com.dc297.ecommerce.orderService.exceptions.NotFoundException;
import com.dc297.ecommerce.orderService.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService{
    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    private final ItemRepository itemRepository;
    private final CustomerRepository customerRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderAddressRepository orderAddressRepository;
    private final OrderPaymentRepository orderPaymentRepository;
    private final OrderPricingRepository orderPricingRepository;

    private final Logger logger = LoggerFactory.getLogger(OrderService.class.getName());

    public OrderService(OrderRepository orderRepository,
                        AddressRepository addressRepository,
                        ItemRepository itemRepository,
                        CustomerRepository customerRepository,
                        PaymentMethodRepository paymentMethodRepository,
                        OrderItemRepository orderItemRepository,
                        OrderAddressRepository orderAddressRepository,
                        OrderPaymentRepository orderPaymentRepository,
                        OrderPricingRepository orderPricingRepository) {
        this.orderRepository = orderRepository;
        this.addressRepository = addressRepository;
        this.itemRepository = itemRepository;
        this.customerRepository = customerRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderAddressRepository = orderAddressRepository;
        this.orderPaymentRepository = orderPaymentRepository;
        this.orderPricingRepository = orderPricingRepository;
    }

    @Override
    public OrderDto create(OrderDto order) {
        if(order.id != null) throw new BadRequestException();
        logger.info("Creating order");

        logger.info("Saving order pricing");
        order.pricing = pricingEntityToDto(orderPricingRepository.save(pricingDtoToEntity(order.pricing)));
        logger.info("Saved order pricing with id {}", order.pricing.id);

        logger.info("Saving order");
        Order savedOrder = orderRepository.save(orderDtoToEntity(order));
        logger.info("Saved order with id {}", savedOrder.id);

        logger.info("Adding items to order");
        savedOrder.items = order.items.parallelStream().map(x -> addItemToOrder(x, savedOrder)).collect(Collectors.toList());
        logger.info("Added items order");

        logger.info("Adding addresses to order");
        savedOrder.addresses = order.addresses.parallelStream().map(x -> addAddressToOrder(x, savedOrder)).collect(Collectors.toList());
        logger.info("Successfully added addresses to order");

        logger.info("Adding payment methods to order");
        savedOrder.payments = order.paymentMethods.parallelStream().map(x -> addPaymentMethodToOrder(x, savedOrder)).collect(Collectors.toList());
        logger.info("Added payment methods to order");

        logger.info("Successfully created order");
        return orderEntityToDto(savedOrder);
    }

    private OrderPayment addPaymentMethodToOrder(PaymentMethodDto x, Order savedOrder) {
        if(x == null || savedOrder == null) return null;
        OrderPayment orderPayment = new OrderPayment();

        orderPayment.paymentMethod = paymentMethodRepository.findById(x.id).orElseThrow(NotFoundException::new);
        orderPayment.order = savedOrder;
        orderPayment.amount = x.amount;
        orderPayment.confirmationNumber = x.confirmationNumber;
        orderPayment.date = x.date;
        return orderPaymentRepository.save(orderPayment);
    }

    private OrderAddress addAddressToOrder(AddressDto x, Order savedOrder) {
        if(x == null || savedOrder == null) return null;
        OrderAddress orderAddress = new OrderAddress();

        orderAddress.order = savedOrder;
        orderAddress.address = addressRepository.findById(x.id).orElseThrow(NotFoundException::new);
        orderAddress.type = x.type;

        return orderAddressRepository.save(orderAddress);
    }

    private OrderItem addItemToOrder(ItemDto x, Order order) {
        if(x == null || order == null) return null;
        OrderItem orderItem = new OrderItem();

        orderItem.order = order;
        orderItem.item = itemRepository.findById(x.id).orElseThrow(NotFoundException::new);
        orderItem.quantity = x.quantity;

        return orderItemRepository.save(orderItem);
    }

    @Override
    public List<OrderDto> findAll() {
        return orderRepository.findAll().stream().map(x -> orderEntityToDto(x)).collect(Collectors.toList());
    }

    @Override
    public OrderDto getById(UUID id) {
        return orderEntityToDto(orderRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public void deleteById(UUID id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void cancel(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(NotFoundException::new);
        order.status = 5;
        orderRepository.save(order);
    }

    private OrderDto orderEntityToDto(Order order){
        if(order == null) return null;

        logger.info("Converting order entity to dto");

        OrderDto output = new OrderDto();
        output.id = order.id;
        output.status = order.status;
        output.shippingMethod = order.shippingMethod;
        output.shippingMethodNotes = order.shippingMethodNotes;

        logger.info("Converting order customer entity to dto {}", order.customer);
        output.customer = customerEntityToDto(order.customer);

        logger.info("Converting order items entity to dto {}", order.items);
        output.items = order.items.stream().map(x -> itemEntityToDto(x)).collect(Collectors.toList());

        logger.info("Converting order addresses entity to dto {}", order.addresses);
        output.addresses = order.addresses.stream().map(x -> addressEntityToDto(x)).collect(Collectors.toList());

        logger.info("Converting order payment methods entity to dto {}", order.payments);
        output.paymentMethods = order.payments.stream().map(x -> paymentMethodEntityToDto(x)).collect(Collectors.toList());

        logger.info("Converting order pricing entity to dto {}", order.pricing);
        output.pricing = pricingEntityToDto(order.pricing);
        return output;
    }

    private PricingDto pricingEntityToDto(OrderPricing pricing) {
        if(pricing == null) return null;
        PricingDto output = new PricingDto();
        output.id = pricing.id;
        output.shippingCharges = pricing.shippingCharges;
        output.subtotal = pricing.subtotal;
        output.tax = pricing.tax;
        output.total = pricing.total;
        return output;
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

    private ItemDto itemEntityToDto(OrderItem x) {
        if(x == null) return null;

        ItemDto output = new ItemDto();
        output.id = x.item.id;
        output.name = x.item.name;
        output.price = x.item.price;
        output.quantity = x.quantity;
        return output;
    }

    private CustomerDto customerEntityToDto(Customer customer) {
        if(customer == null) return null;
        CustomerDto output = new CustomerDto();
        output.id = customer.id;
        output.name = customer.name;
        return output;
    }

    private AddressDto addressEntityToDto(OrderAddress addressInput){
        if(addressInput == null) return null;
        AddressDto output = new AddressDto();
        output.id = addressInput.address.id;
        output.city = addressInput.address.city;
        output.line1 = addressInput.address.line1;
        output.line2 = addressInput.address.line2;
        output.zip = addressInput.address.zip;
        output.state = addressInput.address.state;
        output.type = addressInput.type;
        return output;
    }

    private Order orderDtoToEntity(OrderDto order) {
        if(order == null) return null;
        Order output = new Order();
        output.id = order.id;
        output.status = order.status;
        output.shippingMethod = order.shippingMethod;
        output.shippingMethodNotes = order.shippingMethodNotes;
        output.customer = customerDtoToEntity(order.customer);
        output.pricing = pricingDtoToEntity(order.pricing);
        return output;
    }

    private OrderPricing pricingDtoToEntity(PricingDto pricing) {
        if(pricing == null) return null;
        OrderPricing orderPricing = new OrderPricing();
        orderPricing.id = pricing.id;
        orderPricing.shippingCharges = pricing.shippingCharges;
        orderPricing.subtotal = pricing.subtotal;
        orderPricing.tax = pricing.tax;
        orderPricing.total = pricing.total;
        return orderPricing;
    }


    private Customer customerDtoToEntity(CustomerDto customer) {
        return customerRepository.findById(customer.id).orElseThrow(NotFoundException::new);
    }

}
