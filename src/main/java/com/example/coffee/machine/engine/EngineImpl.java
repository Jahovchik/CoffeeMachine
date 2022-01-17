package com.example.coffee.machine.engine;

import com.example.coffee.machine.model.entity.Order;
import com.example.coffee.machine.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class EngineImpl implements Engine {

    private final ExecutorService executorService;
    private final BlockingQueue<Order> orderQueue;
    private final Logger logger = LoggerFactory.getLogger(EngineImpl.class);

    private OrderService orderService;

    @Autowired
    public EngineImpl() {
        executorService = Executors.newSingleThreadExecutor();
        orderQueue = new LinkedBlockingQueue<>();
    }

    @PostConstruct
    private void init() {
        executorService.execute(this::run);
    }

    @PreDestroy
    private void destroy() {
        executorService.shutdownNow();
    }

    public void addOrderToQueue(Order order) {
        orderQueue.add(order);
    }

    private void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Order order = orderQueue.take();
                executeOrder(order);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.info("Coffee machine finished working");
            }
        }
    }

    private void executeOrder(Order order) throws InterruptedException {
        Thread.sleep(5000);
        orderService.registerOrderCompletion(order);
        logger.info(String.format("%s successfully completed", order));
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
