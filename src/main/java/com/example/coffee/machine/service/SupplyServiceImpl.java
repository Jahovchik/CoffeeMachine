package com.example.coffee.machine.service;

import com.example.coffee.machine.model.dto.SupplyDTO;
import com.example.coffee.machine.model.dto.SupplyMapper;
import com.example.coffee.machine.model.entity.StockMovement;
import com.example.coffee.machine.model.entity.Supply;
import com.example.coffee.machine.persistence.dao.SupplyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class SupplyServiceImpl implements SupplyService {

    private final SupplyMapper supplyMapper;
    private final StockService stockService;
    private final SupplyDAO supplyDAO;

    @Autowired
    public SupplyServiceImpl(SupplyMapper supplyMapper, StockService stockService, SupplyDAO supplyDAO) {
        this.supplyMapper = supplyMapper;
        this.stockService = stockService;
        this.supplyDAO = supplyDAO;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void registerSupply(SupplyDTO supplyDTO) {
        Supply supply = new Supply();
        LocalDateTime supplyDateTime = LocalDateTime.now();
        supply.setDateTime(supplyDateTime);
        StockMovement stockMovement = supplyMapper.supplyDTOToStockMovement(supplyDTO);
        stockService.registerStockMovement(stockMovement, supplyDateTime.toLocalDate());
        supply.setMovement(stockMovement);
        supplyDAO.save(supply);
    }

}
