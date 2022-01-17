package com.example.coffee.machine.web.controller;

import com.example.coffee.machine.model.dto.OrderDTO;
import com.example.coffee.machine.service.OrderService;
import com.example.coffee.machine.service.PreRequestService;
import com.example.coffee.machine.web.exception.NoSuchRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "/order-management")
public class OrderController {

    private final PreRequestService preRequestService;
    private final OrderService orderService;

    @Autowired
    public OrderController(PreRequestService preRequestService, OrderService orderService) {
        this.preRequestService = preRequestService;
        this.orderService = orderService;
    }

    @PostMapping(path = "/orders")
    public String publishOrder(@Valid @RequestBody OrderDTO orderDTO) {
        UUID uuid = preRequestService.save(orderDTO);
        return uuid.toString();
    }

    @PostMapping(path = "/confirmations")
    public OrderDTO approveOrder(@RequestBody String key) {
        UUID uuid = UUID.fromString(key);
        OrderDTO orderDTO = null;
        try {
            orderDTO = (OrderDTO) preRequestService.approve(uuid);
        } catch (ClassCastException exception) {
            throw new NoSuchRequestException("Request not found. Unable to approve.");
        }
        orderService.placeOrder(orderDTO);
        return orderDTO;
    }

}
