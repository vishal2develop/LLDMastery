package WithtoutSingleton;

public class DbConnectionWithoutSingleton {
    public DbConnectionWithoutSingleton() {
        System.out.println("New database connection created.");
    }

    public void connect() {
        System.out.println("Connecting to the database...");
    }
}
