package com.example.coffee.machine.web.controller;

import com.example.coffee.machine.model.dto.SupplyDTO;
import com.example.coffee.machine.service.PreRequestService;
import com.example.coffee.machine.service.SupplyService;
import com.example.coffee.machine.web.exception.NoSuchRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/supply-management")
public class SupplyController {

    private final PreRequestService preRequestService;
    private final SupplyService supplyService;

    @Autowired
    public SupplyController(PreRequestService preRequestService, SupplyService supplyService) {
        this.preRequestService = preRequestService;
        this.supplyService = supplyService;
    }

    @PostMapping(path = "/supplies")
    public String publishSupply(@RequestBody SupplyDTO supplyDTO) {
        UUID uuid = preRequestService.save(supplyDTO);
        return uuid.toString();
    }

    @PostMapping(path = "/confirmations")
    public SupplyDTO approveSupply(@RequestBody String key) {
        UUID uuid = UUID.fromString(key);
        SupplyDTO supplyDTO = null;
        try {
            supplyDTO = (SupplyDTO) preRequestService.approve(uuid);
        } catch (ClassCastException exception) {
            throw new NoSuchRequestException("Request not found. Unable to approve.");
        }
        supplyService.registerSupply(supplyDTO);
        return supplyDTO;
    }
}
