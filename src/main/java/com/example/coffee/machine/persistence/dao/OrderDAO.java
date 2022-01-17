package com.example.coffee.machine.persistence.dao;

import com.example.coffee.machine.model.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderDAO extends CrudRepository<Order, Long> {

}
