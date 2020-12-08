package com.dc297.ecommerce.orderservice.bulk;

import com.dc297.ecommerce.orderservice.dtos.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private KafkaTemplate<String, OrderDto> kafkaTemplate;

    @Value("${kafka.topic.order}")
    private String topic;

    public void send(OrderDto order){
        LOGGER.info("Sending message='{}' to topic='{}'", order, topic);
        kafkaTemplate.send(topic, topic, order);
    }

}
