package HouseBuilder.Director;

import HouseBuilder.Interfaces.HouseBuilder;
import HouseBuilder.Product.House;

public class Engineer {
    private HouseBuilder builder;

    public Engineer(HouseBuilder builder){
        this.builder = builder;
    }

    public House constructHouse(){
        builder.buildWindows();
        builder.buildDoors();
        builder.buildGarage();
        builder.buildSwimmingPool();
        return builder.getHouse();
    }



}
