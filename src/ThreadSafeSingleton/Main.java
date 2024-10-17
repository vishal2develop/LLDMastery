package ThreadSafeSingleton;

public class Main {
    public static void main(String[] args) {
        ThreadSafeSingletonDatabaseConnection databaseConnection1 = ThreadSafeSingletonDatabaseConnection.getInstance();
        databaseConnection1.connect();

        ThreadSafeSingletonDatabaseConnection databaseConnection2 = ThreadSafeSingletonDatabaseConnection.getInstance();
        databaseConnection2.connect();


    }
}
