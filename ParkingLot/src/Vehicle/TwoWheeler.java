package Vehicle;

import Enums.VehicleType;

public class TwoWheeler extends Vehicle {
    public TwoWheeler(String licensePlate) {
        super(licensePlate, VehicleType.TWO_WHEELER);
    }
}
