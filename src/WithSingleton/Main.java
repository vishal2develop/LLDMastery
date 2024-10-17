package WithSingleton;

public class Main {
    public static void main(String[] args) {
        // Get the singleton instance of DatabaseConnection using class.getInstance()
        DatabaseConnection connection1 = DatabaseConnection.getInstance();
        // connect to db instance
        connection1.connect();

        // Try to get another instance of the same class
        DatabaseConnection connection2 = DatabaseConnection.getInstance(); // returns the existing instance
        connection2.connect();

        // Check if both instances are the same
        System.out.println(connection1 == connection2);
    }
}
