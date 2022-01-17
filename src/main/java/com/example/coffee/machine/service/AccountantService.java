package com.example.coffee.machine.service;

import com.example.coffee.machine.model.entity.Order;
import com.example.coffee.machine.model.entity.StockMovement;

public interface AccountantService {

    StockMovement calculateConsumption(Order order);

}
