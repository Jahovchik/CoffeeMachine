package com.example.coffee.machine.model.dto;

import com.example.coffee.machine.model.entity.StockMovement;

public interface SupplyMapper {

    StockMovement supplyDTOToStockMovement(SupplyDTO supplyDTO);

}
