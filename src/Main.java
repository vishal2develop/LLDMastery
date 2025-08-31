import HouseBuilder.Director.Engineer;
import HouseBuilder.Implementation.ModernHouseBuilder;
import HouseBuilder.Interfaces.HouseBuilder;
import HouseBuilder.Product.House;

public class Main {
    public static void main(String[] args) {

        HouseBuilder houseBuilder = new ModernHouseBuilder();
        Engineer engineer = new Engineer(houseBuilder);

        House house = engineer.constructHouse();
        house.show();
    }
}