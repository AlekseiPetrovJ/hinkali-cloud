package ru.petrov.hinkalicloud.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import ru.petrov.hinkalicloud.model.Order;
import ru.petrov.hinkalicloud.repository.OrderRepository;
import ru.petrov.hinkalicloud.services.OrderMessagingService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
    private final OrderRepository orderRepository;
    private final OrderMessagingService messagingService;

    @Autowired
    public OrderController(OrderRepository orderRepository, OrderMessagingService messagingService) {
        this.orderRepository = orderRepository;
        this.messagingService = messagingService;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors,
                               SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        messagingService.sendOrder(order);
        orderRepository.save(order);
        log.info("Order submitted: {}", order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
