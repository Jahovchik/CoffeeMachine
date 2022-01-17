package com.example.coffee.machine.service;

import com.example.coffee.machine.model.dto.OrderDTO;
import com.example.coffee.machine.model.entity.Order;

public interface OrderService {

    void placeOrder(OrderDTO orderDTO);

    void registerOrderCompletion(Order order);

}
