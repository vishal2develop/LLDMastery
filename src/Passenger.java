public class Passenger {
    private final String passengerId;
    private final String name;
    private final int age;
    private final String email;

    public Passenger(String passengerId, String name, int age, String email) {
        this.passengerId = passengerId;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    // getters
    public String getPassengerId() {
        return passengerId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    /**
     * HashCode and Equals must be consistent: equal passengers must have the same hashCode.
     * Both use 'passengerId' as it uniquely identifies each passenger.
     * You MUST override hashCode() when you override equals()
     * Reason - If two objects are equal according to equals(), they MUST return the same hashCode().
     */

    // why? = to compare two passengers
    @Override
    public int hashCode() {
        return passengerId.hashCode();
    }

    // why? = to compare two passengers
    @Override
    public boolean equals(Object obj) {
        // comparing an object to itself = true
        if (this == obj) {
            return true;
        }

        // type check = If you try to compare a Passenger object to a different type of object, it will return false.
        if (!(obj instanceof Passenger)) {
            return false;
        }
        // core check between two passengers. If they have the same passengerId, they are equal.
        return passengerId.equals(
                ((Passenger) obj).passengerId);
    }
}
