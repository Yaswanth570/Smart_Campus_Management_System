import java.sql.*;     // Imports JDBC classes like Connection, PreparedStatement, ResultSet
import java.util.Scanner; // Imports Scanner class for taking user input

public class StudentManagement {

    // Scanner object created once and reused throughout the class
    private final Scanner sc = new Scanner(System.in);

    // Method to add a new student into database
    public void addStudent() {

        try {

            // Get database connection from DBConnection class
            Connection con = DBConnection.getConnection();

            // Ask user for student id
            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();

            // Consume leftover newline character
            sc.nextLine();

            // Ask user for student name
            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            // Ask user for course
            System.out.print("Enter Course: ");
            String course = sc.nextLine();

            // Ask user for year
            System.out.print("Enter Year: ");
            int year = sc.nextInt();

            // Consume leftover newline character
            sc.nextLine();

            // Ask user for email
            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            // SQL query with placeholders
            String query =
                    "INSERT INTO students VALUES(?,?,?,?,?)";

            // Prepare SQL statement
            PreparedStatement pst =
                    con.prepareStatement(query);

            // Replace first ? with student id
            pst.setInt(1, id);

            // Replace second ? with student name
            pst.setString(2, name);

            // Replace third ? with course
            pst.setString(3, course);

            // Replace fourth ? with year
            pst.setInt(4, year);

            // Replace fifth ? with email
            pst.setString(5, email);

            // Execute INSERT query
            int rows = pst.executeUpdate();

            // Check whether insertion happened
            if(rows > 0) {
                System.out.println("Student Added Successfully");
            }

        }
        catch(Exception e) {

            // Print error if anything goes wrong
            System.out.println(e);

        }
    }

    // Method to display all students
    public void viewStudents() {

        try {

            // Get database connection
            Connection con = DBConnection.getConnection();

            // Create SQL statement object
            Statement st = con.createStatement();

            // Execute SELECT query
            ResultSet rs =
                    st.executeQuery("SELECT * FROM students");

            System.out.println("\n===== STUDENT LIST =====");

            // Loop until all rows are printed
            while(rs.next()) {

                // Print each column value
                System.out.println(
                        rs.getInt("student_id") + " | " +
                        rs.getString("student_name") + " | " +
                        rs.getString("course") + " | " +
                        rs.getInt("year") + " | " +
                        rs.getString("email")
                );
            }

        }
        catch(Exception e) {

            System.out.println(e);

        }
    }

    // Method to delete a student
    public void deleteStudent() {

        try {

            // Get database connection
            Connection con = DBConnection.getConnection();

            // Ask user for student id
            System.out.print("Enter Student ID To Delete: ");
            int id = sc.nextInt();

            // SQL query with placeholder
            String query =
                    "DELETE FROM students WHERE student_id=?";

            // Prepare query
            PreparedStatement pst =
                    con.prepareStatement(query);

            // Replace ? with entered id
            pst.setInt(1, id);

            // Execute delete query
            int rows = pst.executeUpdate();

            // Check if row deleted
            if(rows > 0) {

                System.out.println("Student Deleted Successfully");

            }
            else {

                System.out.println("Student Not Found");

            }

        }
        catch(Exception e) {

            System.out.println(e);

        }
    }
 // Method to display attendance records
    public void viewAttendance() {

        try {

            // Get database connection
            Connection con = DBConnection.getConnection();

            // Create statement object
            Statement st = con.createStatement();

            // SQL JOIN query to display student names
            String query =
            "SELECT s.student_name, a.subject_name, a.attendance_date, a.status " +
            "FROM attendance a " +
            "JOIN students s ON a.student_id = s.student_id";

            // Execute query
            ResultSet rs = st.executeQuery(query);

            System.out.println("\n===== ATTENDANCE RECORDS =====");

            while(rs.next()) {

                System.out.println(
                    rs.getString("student_name") + " | " +
                    rs.getString("subject_name") + " | " +
                    rs.getDate("attendance_date") + " | " +
                    rs.getString("status")
                );
            }

        } catch(Exception e) {

            System.out.println(e);
        }
    }
}