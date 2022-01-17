package com.example.coffee.machine.model.dto;

import com.example.coffee.machine.model.entity.StockMovement;
import org.springframework.stereotype.Component;

@Component
public class SupplyMapperImpl implements SupplyMapper {

    public StockMovement supplyDTOToStockMovement(SupplyDTO supplyDTO) {
        StockMovement stockMovement = new StockMovement();
        stockMovement.setCoffeeMovement(supplyDTO.getCoffee());
        stockMovement.setWaterMovement(supplyDTO.getWater());
        stockMovement.setMilkMovement(supplyDTO.getMilk());
        return stockMovement;
    }

}
