package ru.petrov.hinkalicloud.services;

import ru.petrov.hinkalicloud.model.Order;

public interface OrderMessagingService {
    void sendOrder(Order order);
}