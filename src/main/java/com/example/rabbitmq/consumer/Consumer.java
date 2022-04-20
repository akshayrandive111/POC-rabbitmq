package com.example.rabbitmq.consumer;

import com.example.rabbitmq.model.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.example.rabbitmq.config.MessagingConfig.QUEUE;

@Component
public class Consumer {

    @RabbitListener(queues = QUEUE)
    public void consume(OrderStatus orderStatus) {
        System.out.println("Message Received from queue " + orderStatus);
    }
}