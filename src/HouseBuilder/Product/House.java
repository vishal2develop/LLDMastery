package HouseBuilder.Product;

public class House {
    private int windows;
    private int doors;
    private boolean hasGarage;
    private boolean hasSwimmingPool;

    public void setWindows(int windows) { this.windows = windows; }
    public void setDoors(int doors) { this.doors = doors; }
    public void setHasGarage(boolean hasGarage) { this.hasGarage = hasGarage; }
    public void setHasSwimmingPool(boolean hasSwimmingPool) { this.hasSwimmingPool = hasSwimmingPool; }

    public void show() {
        System.out.println("House with " + windows + " windows, " + doors + " doors, " +
                (hasGarage ? "garage" : "no garage") + ", and " +
                (hasSwimmingPool ? "swimming pool." : "no swimming pool."));
    }
}
