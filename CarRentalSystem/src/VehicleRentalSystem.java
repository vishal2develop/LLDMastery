import java.util.List;

public class VehicleRentalSystem {
    List<Store> stores;
    List<User> users;

    VehicleRentalSystem(List<Store> stores,List<User> users){
        this.stores = stores;
        this.users = users;
    }

    public  Store getStore(Location location){
        // get store based on location
        return stores.get(0);
    }

    // add users, remove users

    // add stores, remove stores
}
