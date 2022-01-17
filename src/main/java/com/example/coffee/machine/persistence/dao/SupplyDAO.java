package com.example.coffee.machine.persistence.dao;

import com.example.coffee.machine.model.entity.Supply;
import org.springframework.data.repository.CrudRepository;

public interface SupplyDAO extends CrudRepository<Supply, Long> {
}
