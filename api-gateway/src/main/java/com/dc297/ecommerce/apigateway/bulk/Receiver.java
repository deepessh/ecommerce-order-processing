package com.dc297.ecommerce.apigateway.bulk;

import com.dc297.ecommerce.apigateway.dtos.OrderDto;
import com.dc297.ecommerce.apigateway.dtos.OrderStatusDto;
import com.dc297.ecommerce.apigateway.services.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class Receiver {
    private final IOrderService orderService;
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    Receiver(IOrderService orderService) {
        this.orderService = orderService;
    }

    @KafkaListener(id = "kafka-batch", topics = "${kafka.topic.order}", containerFactory = "kafkaListenerContainerFactory")
    public void receive(@Payload List<OrderDto> messages) {

        for (int i = 0; i < messages.size(); i++) {
            orderService.create(messages.get(i));
        }
        LOGGER.info("All order creation batch messages received");
    }

    @KafkaListener(id = "kafka-batcha", topics = "${kafka.topic.orderstatus}", containerFactory = "kafkaListenerContainerFactoryOrderStatus")
    public void receiveOrderStatus(@Payload List<OrderStatusDto> messages) {

        for (int i = 0; i < messages.size(); i++) {
            OrderStatusDto orderStatusDto = messages.get(i);
            orderService.updateStatus(orderStatusDto.orderId, orderStatusDto.status);
        }
        LOGGER.info("All order status batch messages received");
    }

}
