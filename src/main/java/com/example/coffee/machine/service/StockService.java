package com.example.coffee.machine.service;

import com.example.coffee.machine.model.entity.StockMovement;

import java.time.LocalDate;

public interface StockService {

    void registerStockMovement(StockMovement stockMovement, LocalDate localDate);

}
