package com.example.coffee.machine.model.entity;

import com.example.coffee.machine.model.enums.EspressoBasedCoffeeType;
import com.example.coffee.machine.model.enums.GrindSize;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movement_id")
    @MapsId
    private StockMovement movement;

    @Column(name = "data_time")
    private LocalDateTime dateTime;

    @Column(name = "coffee_type")
    private EspressoBasedCoffeeType coffeeType;

    @Column(name = "strong")
    private boolean strong = false;

    @Column(name = "grind_size")
    private GrindSize grindSize;

    @Column(name = "temperature")
    private Integer temperature;

    @Column(name = "completed")
    private Boolean completed = false;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StockMovement getMovement() {
        return movement;
    }

    public void setMovement(StockMovement movement) {
        this.movement = movement;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public EspressoBasedCoffeeType getCoffeeType() {
        return coffeeType;
    }

    public void setCoffeeType(EspressoBasedCoffeeType coffeeType) {
        this.coffeeType = coffeeType;
    }

    public Boolean getStrong() {
        return strong;
    }

    public void setStrong(Boolean strong) {
        this.strong = strong;
    }

    public GrindSize getGrindSize() {
        return grindSize;
    }

    public void setGrindSize(GrindSize grindSize) {
        this.grindSize = grindSize;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Order{" +
                "dateTime=" + dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) +
                ", coffeeType=" + coffeeType +
                ", strong=" + strong +
                ", grindSize=" + grindSize +
                ", temperature=" + temperature +
                '}';
    }
}
