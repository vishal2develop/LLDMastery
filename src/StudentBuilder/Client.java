package StudentBuilder;

import java.util.Arrays;

public class Client {
    public static void main(String[] args) {
        Director director1 = new Director(new EngineeringStudentBuilder());
        Director director2 = new Director(new MBAStudentBuilder());

        Student engStudent = director1.createStudent();
        Student mbaStudent = director2.createStudent();

        System.out.println(engStudent.toString());
        System.out.println(mbaStudent.toString());
    }
}
