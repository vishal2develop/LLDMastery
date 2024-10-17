package WithSingleton;

public class DatabaseConnection {
    // Step 1: Create a static instance of the class
    private static DatabaseConnection instance;

    // Step 2: Private Constructor
    private DatabaseConnection(){
        System.out.println("New database connection created.");
    }

    // getInstance() to get access to singleton
    public static DatabaseConnection getInstance(){
        if(instance == null){
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void connect(){
        System.out.println("Database connection established.");
    }


}
