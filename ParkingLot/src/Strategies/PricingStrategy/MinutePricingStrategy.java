package Strategies.PricingStrategy;

import Interfaces.PricingStrategy;

public class MinutePricingStrategy implements PricingStrategy {
    @Override
    public double calculateCost(long entryTime) {
        return (System.currentTimeMillis() - entryTime)*5;
    }
}
