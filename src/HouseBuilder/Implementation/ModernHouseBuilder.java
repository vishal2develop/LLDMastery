package HouseBuilder.Implementation;

import HouseBuilder.Interfaces.HouseBuilder;
import HouseBuilder.Product.House;

public class ModernHouseBuilder implements HouseBuilder {
    private House house = new House();

    @Override
    public void buildWindows() {
        house.setWindows(4);
    }

    @Override
    public void buildDoors() {
        house.setDoors(2);
    }

    @Override
    public void buildGarage() {
        house.setHasGarage(true);
    }

    @Override
    public void buildSwimmingPool() {
        house.setHasSwimmingPool(true);
    }

    @Override
    public House getHouse() {
        return house;
    }
}
