package com.example.rabbitmq.publisher;

import com.example.rabbitmq.model.Order;
import com.example.rabbitmq.model.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.example.rabbitmq.config.MessagingConfig.ROUTING_KEY;
import static com.example.rabbitmq.config.MessagingConfig.TOPIC_EXCHANGE;

@RestController
@RequestMapping("/order")
public class OrderPublisherController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {
        order.setId(UUID.randomUUID().toString());
        OrderStatus orderStatus = new OrderStatus(order, "In Progress", "Order placed successfully in restaurant " + restaurantName);
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE, ROUTING_KEY, orderStatus);
        return "Order placed successfully!";
    }
}
