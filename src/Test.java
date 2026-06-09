public class Test {

    public static void main(String[] args) {

        StudentManagement student =
                new StudentManagement();

        student.viewStudents();

        student.deleteStudent();

        student.viewStudents();

    }
}