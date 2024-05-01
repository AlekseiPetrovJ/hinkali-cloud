package ru.petrov.hinkalicloud.repository;

import org.springframework.data.repository.CrudRepository;
import ru.petrov.hinkalicloud.model.Order;

import java.util.UUID;

public interface OrderRepository  extends CrudRepository<Order, UUID> {
}
