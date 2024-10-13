package Strategies.PricingStrategy;

import Interfaces.PricingStrategy;

public class HourlyPricingStrategy implements PricingStrategy {
    @Override
    public double calculateCost(long entryTime,double parkingSpotCost) {
        return (System.currentTimeMillis() - entryTime)*parkingSpotCost;
    }
}
