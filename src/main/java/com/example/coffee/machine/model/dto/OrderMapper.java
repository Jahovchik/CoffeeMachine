package com.example.coffee.machine.model.dto;

import com.example.coffee.machine.model.entity.Order;

public interface OrderMapper {

    Order orderDTOtoOrder(OrderDTO orderDTO);

    OrderDTO orderToOrderDTO(Order order);

}
