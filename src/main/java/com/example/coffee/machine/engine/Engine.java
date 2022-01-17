package com.example.coffee.machine.engine;

import com.example.coffee.machine.model.entity.Order;

public interface Engine {

    void addOrderToQueue(Order order);

}
