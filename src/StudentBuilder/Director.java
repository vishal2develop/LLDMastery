package StudentBuilder;

public class Director {
    StudentBuilder studentBuilder;
    Director(StudentBuilder studentBuilder){
        this.studentBuilder = studentBuilder;
    }
    public Student createStudent(){
        if(studentBuilder instanceof EngineeringStudentBuilder){
            return createEngineeringStudent();
        }
        else if(studentBuilder instanceof MBAStudentBuilder){
            return createMBAStudent();
        }
        return null;
    }

    public Student createEngineeringStudent(){
        return studentBuilder.setRollNumber(1).setAge(21).setName("JOHN").setSubjects().build();
    }

    public Student createMBAStudent(){
        return studentBuilder.setRollNumber(2).setAge(22).setName("JANE").setFatherName("Jack").setMotherName("Jill").setSubjects().build();
    }
}
