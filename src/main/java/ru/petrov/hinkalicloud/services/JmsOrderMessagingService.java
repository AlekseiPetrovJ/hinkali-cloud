package ru.petrov.hinkalicloud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import ru.petrov.hinkalicloud.model.Order;

@Service
public class JmsOrderMessagingService implements OrderMessagingService{
    private final JmsTemplate jms;

    @Autowired
    public JmsOrderMessagingService(JmsTemplate jms) {
        this.jms = jms;
    }

    @Override
    public void sendOrder(Order order) {
        jms.convertAndSend("hinkali.cloud.queue",order);
    }
}