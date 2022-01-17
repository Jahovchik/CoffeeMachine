package com.example.coffee.machine.service;

import com.example.coffee.machine.model.entity.Order;
import com.example.coffee.machine.model.entity.StockMovement;
import com.example.coffee.machine.model.enums.EspressoBasedCoffeeType;
import org.springframework.stereotype.Service;

@Service
public class AccountantServiceImpl implements AccountantService {

    private static final byte COFFEE_SHOT_NORMAL = 7;
    private static final byte COFFEE_SHOT_STRONG = 10;
    private static final byte ONE_SHOT_WATER = 30;

    public StockMovement calculateConsumption(Order order) {
        EspressoBasedCoffeeType coffeeType = order.getCoffeeType();
        boolean strong = order.getStrong();
        byte coffeeDose = strong ? COFFEE_SHOT_STRONG : COFFEE_SHOT_NORMAL;
        int coffeeConsumption = -(coffeeType.getEspressoShots() * coffeeDose);
        int waterConsumption = -(coffeeType.getEspressoShots() * ONE_SHOT_WATER + coffeeType.getAdditionalWater());
        int milkConsumption = -(coffeeType.getMilkVolume());
        return new StockMovement(coffeeConsumption, waterConsumption, milkConsumption);
    }

}
