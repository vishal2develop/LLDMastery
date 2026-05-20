import java.util.ArrayList;
import java.util.List;

// This should manage searchable vehicles
public class VehicleInventory {
    private List<Vehicle> vehicles;
    public VehicleInventory(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Vehicle> searchAvailableVehicles(VehicleType vehicleType) {
        // Filter all available vehicles of a given vehicleType
        return vehicles.stream()
                .filter(vehicle -> vehicle.getType() == vehicleType)
                .filter(vehicle -> vehicle.getStatus() == VehicleStatus.AVAILABLE)
                .toList();
    }
}
