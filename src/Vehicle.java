public class Vehicle {
    private String vehicleId;
    private String licenseNumber;
    private VehicleType type;
    private VehicleStatus status;

    public Vehicle(String vehicleId, String licenseNumber, VehicleType type, VehicleStatus status) {
        this.vehicleId = vehicleId;
        this.licenseNumber = licenseNumber;
        this.type = type;
        this.status = status;
    }

    public synchronized boolean reserveVehicle() {
        if (status != VehicleStatus.AVAILABLE) {
            return false;
        }
        status = VehicleStatus.RESERVED;
        return true;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public VehicleType getType() {
        return type;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }
}
