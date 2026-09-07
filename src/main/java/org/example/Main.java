import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        if (authenticate()) {
            while (true) {
                System.out.println("\n--- Student Management System ---");
                System.out.println("1. Add Student");
                System.out.println("2. View Student Information");
                System.out.println("3. Search Student by ID");
                System.out.println("4. Assign courses to Student");
                System.out.println("5. View Advised Courses");
                System.out.println("6. Delete Student");
                System.out.println("7. Exit Program");
                System.out.print("Enter your choice: ");
                String option = input.nextLine();
                switch (option) {
                    case "1": addStudent();
                        break;
                    case "2": viewStudent();
                        break;
                    case "3": searchStudent();
                        break;
                    case "4": assignCourse();
                        break;
                    case "5": viewCourses();
                        break;
                    case "6": deleteStudent();
                        break;
                    case "7":
                        System.out.println("Program exited.");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice.");

                }
            }
        }
    }


    // ================= AUTHENTICATION =================
    static boolean authenticate() {
        System.out.println("--- Login ---");
        System.out.print("Username: ");
        String username = input.nextLine();
        System.out.print("Password: ");
        String password = input.nextLine();
        try {
            RandomAccessFile file = new RandomAccessFile("src/main/resources/users.txt", "r");
            String line;
            while ((line = file.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    if (username.equals(parts[0]) && password.equals(parts[1])) {
                        file.close();
                        System.out.println("Login successful.");
                        return true;

                    }
                }
            }
            file.close();
            System.out.println("Invalid username or password.");

        }
        catch (IOException e) {
            System.out.println("Error reading user file.");
        }
        return false;

    }


    // ================= ADD STUDENT =================
    static void addStudent() {
        try {
            RandomAccessFile file = new RandomAccessFile("src/main/resources/students.txt", "rw");
            file.seek(file.length());
            System.out.print("Enter Student ID: ");
            String id = input.nextLine();
            System.out.print("Enter Student Name: ");
            String name = input.nextLine();
            System.out.print("Program: ");
            String program = input.nextLine();
            System.out.print("Batch: ");
            String batch = input.nextLine();
            System.out.print("CGPA: ");
            String cgpa = input.nextLine();
            System.out.print("Password: ");
            String password = input.nextLine();
            file.writeBytes(id + "," + name + "," + program + "," + batch + "," + cgpa + "," + password + "\n");
            file.close();
            System.out.println("Student added successfully.");


        }
        catch (IOException e) {
            System.out.println("Error adding student.");
        }


    }


    // ================= VIEW STUDENTS =================
    static void viewStudent() {
        try {
            RandomAccessFile file = new RandomAccessFile("src/main/resources/students.txt", "r");
            String line;
            boolean found = false;
            System.out.println("\n--- Students List ---");

            while ((line = file.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    System.out.println("-------------------------");
                    System.out.println("Student ID: " + parts[0]);
                    System.out.println("Student Name: " + parts[1]);
                    System.out.println("Program: " + parts[2]);
                    System.out.println("Batch: " + parts[3]);
                    System.out.println("CGPA: " + parts[4]);
                    found = true;

                }
            }
            file.close();
            if (!found) {
                System.out.println("No students found.");
            }

        }
        catch (IOException e) {
            System.out.println("No student file found.");
        }


    }


    // ================= SEARCH STUDENT =================
    static void searchStudent() {
        System.out.print("Enter Student ID: ");
        String id = input.nextLine();
        try {
            RandomAccessFile file = new RandomAccessFile("src/main/resources/students.txt", "r");
            String line;
            boolean found = false;
            while ((line = file.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    if (parts[0].equals(id)) {
                        System.out.println("\n--- Student Information ---");
                        System.out.println("Student ID: " + parts[0]);
                        System.out.println("Name: " + parts[1]);
                        System.out.println("Program: " + parts[2]);
                        System.out.println("Batch: " + parts[3]);
                        System.out.println("CGPA: " + parts[4]);
                        found = true;
                        break;

                    }
                }
            }
            file.close();
            if (!found) {
                System.out.println("Student not found.");
            }

        }
        catch (IOException e) {
            System.out.println("Error searching student.");
        }


    }


    // ================= ASSIGN COURSE =================
    static void assignCourse() {
        System.out.print("Enter Student ID: ");
        String id = input.nextLine();
        try {
            RandomAccessFile studentFile = new RandomAccessFile("src/main/resources/students.txt", "r");
            String line;
            boolean studentFound = false;
            while ((line = studentFile.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    if (parts[0].equals(id)) {
                        studentFound = true;
                        break;

                    }
                }
            }

            studentFile.close();
            if (!studentFound) {
                System.out.println("Student not found.");
                return;

            }

            System.out.print("Enter Course Name: ");
            String course = input.nextLine();

            RandomAccessFile courseFile = new RandomAccessFile("src/main/resources/courses.txt", "rw");
            courseFile.seek(courseFile.length());
            courseFile.writeBytes(id + "," + course + "\n");
            courseFile.close();
            System.out.println("Course assigned successfully.");


        }
        catch (IOException e) {
            System.out.println("Error assigning course.");
        }


    }


    // ================= VIEW COURSES =================
    static void viewCourses() {
        System.out.print("Enter Student ID: ");
        String id = input.nextLine();
        try {
            RandomAccessFile file = new RandomAccessFile("src/main/resources/courses.txt", "r");
            String line;
            boolean found = false;
            System.out.println("\n--- Advised Courses ---");
            while ((line = file.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    if (parts[0].equals(id)) {
                        System.out.println("- " + parts[1]);
                        found = true;

                    }
                }
            }

            file.close();
            if (!found) {
                System.out.println("No courses assigned.");
            }

        }
        catch (IOException e) {
            System.out.println("No courses found.");
        }


    }


    // ================= DELETE STUDENT =================
    static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        String id = input.nextLine();
        try {
            RandomAccessFile file = new RandomAccessFile("src/main/resources/students.txt", "r");
            StringBuilder data = new StringBuilder();
            String line;
            boolean found = false;
            while ((line = file.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6 && parts[0].equals(id)) {
                    found = true;

                }
                else {
                    data.append(line).append("\n");
                }

            }

            file.close();
            if (!found) {
                System.out.println("Student not found.");
                return;

            }

            file = new RandomAccessFile("src/main/resources/students.txt", "rw");
            file.setLength(0);
            file.writeBytes(data.toString());
            file.close();
            try {
                RandomAccessFile courseFile = new RandomAccessFile("src/main/resources/courses.txt", "r");
                StringBuilder courseData = new StringBuilder();
                while ((line = courseFile.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 2 && parts[0].equals(id)) {
                        continue;
                    }
                    courseData.append(line).append("\n");

                }
                courseFile.close();
                courseFile = new RandomAccessFile("src/main/resources/courses.txt", "rw");
                courseFile.setLength(0);
                courseFile.writeBytes(courseData.toString());
                courseFile.close();

            }
            catch (IOException e) {
                // courses.txt may not exist
            }

            System.out.println("Student deleted successfully.");
        }
        catch (IOException e) {
            System.out.println("Error deleting student.");
        }


    }


}