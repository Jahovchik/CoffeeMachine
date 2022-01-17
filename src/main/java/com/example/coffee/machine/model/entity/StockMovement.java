package com.example.coffee.machine.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "stock_movements")
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "movement", fetch = FetchType.LAZY)
    private Order order;

    @OneToOne(mappedBy = "movement", fetch = FetchType.LAZY)
    private Supply supply;

    @Column(name = "coffee_movement")
    private Integer coffeeMovement;

    @Column(name = "water_movement")
    private Integer waterMovement;

    @Column(name = "milk_movement")
    private Integer milkMovement;

    public StockMovement() {
    }

    public StockMovement(Integer coffeeMovement, Integer waterMovement, Integer milkMovement) {
        this.coffeeMovement = coffeeMovement;
        this.waterMovement = waterMovement;
        this.milkMovement = milkMovement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCoffeeMovement() {
        return coffeeMovement;
    }

    public void setCoffeeMovement(Integer coffeeMovement) {
        this.coffeeMovement = coffeeMovement;
    }

    public Integer getWaterMovement() {
        return waterMovement;
    }

    public void setWaterMovement(Integer waterMovement) {
        this.waterMovement = waterMovement;
    }

    public Integer getMilkMovement() {
        return milkMovement;
    }

    public void setMilkMovement(Integer milkMovement) {
        this.milkMovement = milkMovement;
    }
}
