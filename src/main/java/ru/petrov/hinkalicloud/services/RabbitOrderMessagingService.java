package ru.petrov.hinkalicloud.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.petrov.hinkalicloud.model.Order;

@Service
public class RabbitOrderMessagingService implements OrderMessagingService{
    private final RabbitTemplate rabbit;
    @Autowired
    public RabbitOrderMessagingService(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }
    @Override
    public void sendOrder(Order order) {
        rabbit.convertAndSend(order);
    }
}