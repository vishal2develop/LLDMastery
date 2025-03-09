import Product.Vehicle;

import java.util.List;

public class VehicleInventoryManagement {
    List <Vehicle> vehicles;

    public VehicleInventoryManagement(List<Vehicle> vehicles){
        this.vehicles = vehicles;
    }

    public List<Vehicle> getVehicles() {
        // any kind of filtering you may want to apply
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
