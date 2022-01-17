package com.example.coffee.machine.persistence.dao;

import com.example.coffee.machine.model.entity.StockMovement;
import org.springframework.data.repository.CrudRepository;

public interface StockMovementDAO extends CrudRepository<StockMovement, Long> {
}
