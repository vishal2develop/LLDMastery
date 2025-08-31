package HouseBuilder.Interfaces;

import HouseBuilder.Product.House;

public interface HouseBuilder {
    void buildWindows();
    void buildDoors();
    void buildGarage();
    void buildSwimmingPool();
    House getHouse();
}
