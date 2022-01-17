package com.example.coffee.machine.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "supplies")
public class Supply {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movement_id")
    @MapsId
    private StockMovement movement;

    @Column(name = "data_time")
    private LocalDateTime dateTime;

    public Supply() {
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
}
