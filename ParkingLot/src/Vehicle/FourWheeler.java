package Vehicle;

import Enums.VehicleType;

public class FourWheeler extends Vehicle {
    public FourWheeler(String licensePlate) {
        super(licensePlate, VehicleType.FOUR_WHEELER);
    }
}
