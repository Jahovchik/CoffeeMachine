package com.example.coffee.machine.persistence.dao;

import com.example.coffee.machine.model.entity.StockBalance;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface StockBalanceDAO extends CrudRepository<StockBalance, LocalDate> {
    StockBalance findFirstByOrderByDateDesc();
}
