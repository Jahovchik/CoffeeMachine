package com.example.coffee.machine.service;

import com.example.coffee.machine.model.entity.StockBalance;
import com.example.coffee.machine.model.entity.StockMovement;
import com.example.coffee.machine.persistence.dao.StockBalanceDAO;
import com.example.coffee.machine.persistence.dao.StockMovementDAO;
import com.example.coffee.machine.service.exception.IllegalBalanceException;
import com.example.coffee.machine.service.exception.IllegalChronologyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class StockServiceImpl implements StockService {

    private static final int COFFEE_CAPACITY = 280;
    private static final int WATER_CAPACITY = 2400;
    private static final int MILK_CAPACITY = 1200;

    private final StockMovementDAO stockMovementDAO;
    private final StockBalanceDAO stockBalanceDAO;

    @Autowired
    public StockServiceImpl(StockMovementDAO stockMovementDAO, StockBalanceDAO stockBalanceDAO) {
        this.stockMovementDAO = stockMovementDAO;
        this.stockBalanceDAO = stockBalanceDAO;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void registerStockMovement(StockMovement stockMovement, LocalDate localDate) {
        StockBalance newStockBalance = checkNewBalance(stockMovement, localDate);
        stockBalanceDAO.save(newStockBalance);
        stockMovementDAO.save(stockMovement);
    }

    private StockBalance checkNewBalance(StockMovement stockMovement, LocalDate localDate) {
        StockBalance stockBalance = stockBalanceDAO.findFirstByOrderByDateDesc();
        if (stockBalance.getDate().compareTo(localDate) > 0) {
            throw new IllegalChronologyException("Cannot handle balances of future date");
        }
        if (stockBalance.getDate().compareTo(localDate) < 0) {
            stockBalance = new StockBalance(stockBalance);
            stockBalance.setDate(localDate);
        }
        stockBalance.addStockMovement(stockMovement);
        int coffeeBalance = stockBalance.getCoffeeBalance();
        int waterBalance = stockBalance.getWaterBalance();
        int milkBalance = stockBalance.getMilkBalance();
        if (coffeeBalance < 0 || coffeeBalance > COFFEE_CAPACITY
                || waterBalance < 0 || waterBalance > WATER_CAPACITY
                || milkBalance < 0 || milkBalance > MILK_CAPACITY) {
            throw new IllegalBalanceException(errorMessage(coffeeBalance, waterBalance, milkBalance));
        }
        return stockBalance;
    }

    private String errorMessage(int coffeeBalance, int waterBalance, int milkBalance) {
        return String.format("Illegal balances state during transaction "
                        + "Coffee: %s, Water: %s, Milk: %s " +
                        "Capacities - Coffee: %s, Water: %s, Milk: %s",
                coffeeBalance, waterBalance, milkBalance,
                COFFEE_CAPACITY, WATER_CAPACITY, MILK_CAPACITY);
    }

}
