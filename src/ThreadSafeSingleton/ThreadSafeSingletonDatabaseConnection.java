package ThreadSafeSingleton;

public class ThreadSafeSingletonDatabaseConnection {
    private static ThreadSafeSingletonDatabaseConnection instance;

    private ThreadSafeSingletonDatabaseConnection() {
        System.out.println("New database connection created.");
    }

    public static synchronized ThreadSafeSingletonDatabaseConnection getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingletonDatabaseConnection();
        }
        return instance;
    }

    public void connect(){
        System.out.println("Database connection established.");
    }
}
