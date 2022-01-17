package com.example.coffee.machine.model.dto;

import com.example.coffee.machine.model.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperImpl implements OrderMapper {

    public Order orderDTOtoOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setCoffeeType(orderDTO.getCoffeeType());
        order.setStrong(orderDTO.getStrong());
        order.setGrindSize(orderDTO.getGrindSize());
        order.setTemperature(orderDTO.getTemperature());
        return order;
    }

    public OrderDTO orderToOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCoffeeType(order.getCoffeeType());
        orderDTO.setStrong(order.getStrong());
        orderDTO.setGrindSize(order.getGrindSize());
        orderDTO.setTemperature(order.getTemperature());
        return orderDTO;
    }

}
