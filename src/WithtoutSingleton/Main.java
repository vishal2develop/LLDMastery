package WithtoutSingleton;

public class Main {
    public static void main(String[] args) {
        // 1st object
        DbConnectionWithoutSingleton db1 = new DbConnectionWithoutSingleton();
        db1.connect();

        // 2nd object - Multiple Objects can be created
        DbConnectionWithoutSingleton db2 = new DbConnectionWithoutSingleton();
        db2.connect();

    }
}
