package com.example.coffee.machine.service;

import com.example.coffee.machine.engine.Engine;
import com.example.coffee.machine.model.dto.OrderDTO;
import com.example.coffee.machine.model.dto.OrderMapper;
import com.example.coffee.machine.model.entity.Order;
import com.example.coffee.machine.model.entity.StockMovement;
import com.example.coffee.machine.model.enums.GrindSize;
import com.example.coffee.machine.persistence.dao.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {

    public static final boolean DEFAULT_STRONG = false;
    public static final GrindSize DEFAULT_GRIND_SIZE = GrindSize.MEDIUM;
    public static final int DEFAULT_TEMPERATURE = 92;

    private final OrderMapper orderMapper;
    private final AccountantService accountantService;
    private final StockService stockService;
    private final OrderDAO orderDAO;
    private final Engine engine;

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper, AccountantService accountantService, StockService stockService, OrderDAO orderDAO, Engine engine) {
        this.orderMapper = orderMapper;
        this.accountantService = accountantService;
        this.stockService = stockService;
        this.orderDAO = orderDAO;
        this.engine = engine;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void placeOrder(OrderDTO orderDTO) {
        Order order = orderMapper.orderDTOtoOrder(orderDTO);
        checkFields(order);
        LocalDateTime orderDateTime = LocalDateTime.now();
        order.setDateTime(orderDateTime);
        StockMovement stockMovement = accountantService.calculateConsumption(order);
        stockService.registerStockMovement(stockMovement, orderDateTime.toLocalDate());
        order.setMovement(stockMovement);
        orderDAO.save(order);
        engine.addOrderToQueue(order);
    }

    @Transactional
    public void registerOrderCompletion(Order order) {
        order = orderDAO.findById(order.getId()).orElseThrow(() -> new IllegalArgumentException("No order found"));
        order.setCompleted(true);
        orderDAO.save(order);
    }

    private void checkFields(Order order) {
        if (order.getCoffeeType() == null) {
            throw new IllegalArgumentException("Coffee type cannot be null");
        }
        if (order.getStrong() == null) {
            order.setStrong(DEFAULT_STRONG);
        }
        if (order.getGrindSize() == null) {
            order.setGrindSize(DEFAULT_GRIND_SIZE);
        }
        if (order.getTemperature() == null) {
            order.setTemperature(DEFAULT_TEMPERATURE);
        }
    }

}
