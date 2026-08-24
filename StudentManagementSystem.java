
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class StudentManagementSystem {

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n=================================");
            System.out.println("      STUDENT MANAGEMENT SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Student Statistics");
            System.out.println("7. Sort Students");
            System.out.println("8. Exit");
            System.out.println("=================================");

            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    updateStudent();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 6:
                    showStatistics();
                    break;

                case 7:
                    sortStudents();
                    break;

                case 8:
                    System.out.println("\nThank you for using the Student Management System!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter 1-6.");
            }

        } while (choice != 8);

        scanner.close();
    }

    public static int readInt(String message) {

        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public static String readText(String message) {

        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("Input cannot be empty.");
        }
    }

    public static int readAge(String message) {

        while (true) {
            int age = readInt(message);

            if (age >= 5 && age <= 100) {
                return age;
            }

            System.out.println("Please enter a valid age between 5 and 100.");
        }
    }

    public static double readMarks(String message) {

        while (true) {
            try {
                System.out.print(message);
                double marks = Double.parseDouble(scanner.nextLine());

                if (marks >= 0 && marks <= 100) {
                    return marks;
                }

                System.out.println("Marks must be between 0 and 100.");

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public static void addStudent() {

        System.out.println("\n--- Add Student ---");

        int id = readInt("Enter Student ID: ");

        for (Student student : students) {
            if (student.getStudentId() == id) {
                System.out.println("Student ID already exists.");
                return;
            }
        }

        String name = readText("Enter Student Name: ");
        int age = readAge("Enter Age: ");
        String course = readText("Enter Course: ");
        double marks = readMarks("Enter Marks (0-100): ");

        Student student = new Student(id, name, age, course, marks);
        students.add(student);

        System.out.println("Student added successfully!");
    }

    public static void viewStudents() {

        System.out.println("\n==================== STUDENT RECORDS ====================");

        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        System.out.println("Total Students: " + students.size());
        System.out.println("----------------------------------------------------------");

        for (Student student : students) {
            System.out.println(student);
        }

        System.out.println("==========================================================");
    }

    public static void searchStudent() {

        System.out.println("\n--- Search Student ---");

        int id = readInt("Enter Student ID: ");

        for (Student student : students) {

            if (student.getStudentId() == id) {
                System.out.println("\nStudent Found:");
                System.out.println(student);
                return;
            }
        }

        System.out.println("Student not found.");
    }

    public static void updateStudent() {

        System.out.println("\n--- Update Student ---");

        int id = readInt("Enter Student ID: ");

        for (Student student : students) {

            if (student.getStudentId() == id) {

                String name = readText("Enter New Name: ");
                int age = readAge("Enter New Age: ");
                String course = readText("Enter New Course: ");
                double marks = readMarks("Enter New Marks (0-100): ");

                student.setName(name);
                student.setAge(age);
                student.setCourse(course);
                student.setMarks(marks);

                System.out.println("Student information updated successfully!");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    public static void deleteStudent() {

        System.out.println("\n--- Delete Student ---");

        int id = readInt("Enter Student ID: ");

        for (Student student : students) {

            if (student.getStudentId() == id) {

                System.out.println("Student found: " + student);

                System.out.print("Are you sure you want to delete this student? (yes/no): ");
                String confirmation = scanner.nextLine().trim();

                if (confirmation.equalsIgnoreCase("yes")) {
                    students.remove(student);
                    System.out.println("Student deleted successfully!");
                } else {
                    System.out.println("Delete operation cancelled.");
                }

                return;
            }
        }

        System.out.println("Student not found.");
    }

    public static void showStatistics() {

        System.out.println("\n--- Student Statistics ---");

        if (students.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        double totalMarks = 0;
        double highest = students.get(0).getMarks();
        double lowest = students.get(0).getMarks();

        for (Student student : students) {

            double marks = student.getMarks();

            totalMarks += marks;

            if (marks > highest) {
                highest = marks;
            }

            if (marks < lowest) {
                lowest = marks;
            }
        }

        double average = totalMarks / students.size();

        System.out.println("Total Students : " + students.size());
        System.out.printf("Average Marks  : %.2f%n", average);
        System.out.printf("Highest Marks  : %.2f%n", highest);
        System.out.printf("Lowest Marks   : %.2f%n", lowest);
    }

    public static void sortStudents() {

        if (students.isEmpty()) {
            System.out.println("\nNo student records available.");
            return;
        }

        System.out.println("\n--- Sort Students ---");
        System.out.println("1. Sort by Student ID");
        System.out.println("2. Sort by Name");

        int choice = readInt("Enter your choice: ");

        switch (choice) {

            case 1:
                Collections.sort(
                        students,
                        Comparator.comparingInt(Student::getStudentId)
                );

                System.out.println("Students sorted by ID.");
                break;

            case 2:
                Collections.sort(
                        students,
                        Comparator.comparing(Student::getName, String.CASE_INSENSITIVE_ORDER)
                );

                System.out.println("Students sorted by Name.");
                break;

            default:
                System.out.println("Invalid sorting choice.");
        }
    }
}
