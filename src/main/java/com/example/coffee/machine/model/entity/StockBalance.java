package com.example.coffee.machine.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "stock_balances")
public class StockBalance {

    @Id
    @Column(name = "date")
    LocalDate date;

    @Column(name = "coffee_balance")
    private Integer coffeeBalance;

    @Column(name = "water_balance")
    private Integer waterBalance;

    @Column(name = "milk_balance")
    private Integer milkBalance;

    public StockBalance() {
    }

    public StockBalance(StockBalance stockBalance) {
        this.coffeeBalance = stockBalance.getCoffeeBalance();
        this.waterBalance = stockBalance.getWaterBalance();
        this.milkBalance = stockBalance.getMilkBalance();
    }

    public void addStockMovement(StockMovement stockMovement) {
        coffeeBalance += stockMovement.getCoffeeMovement();
        waterBalance += stockMovement.getWaterMovement();
        milkBalance += stockMovement.getMilkMovement();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getCoffeeBalance() {
        return coffeeBalance;
    }

    public void setCoffeeBalance(Integer coffeeBalance) {
        this.coffeeBalance = coffeeBalance;
    }

    public Integer getWaterBalance() {
        return waterBalance;
    }

    public void setWaterBalance(Integer waterBalance) {
        this.waterBalance = waterBalance;
    }

    public Integer getMilkBalance() {
        return milkBalance;
    }

    public void setMilkBalance(Integer milkBalance) {
        this.milkBalance = milkBalance;
    }
}
