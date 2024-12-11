/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Studenttt {
    private int id;
    private String name;
    private double score;

    public Studenttt(int id, String name, double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getRank() {
        if (score < 5.0) return "Fail";
        else if (score < 6.5) return "Medium";
        else if (score < 7.5) return "Good";
        else if (score < 9.0) return "Very Good";
        else return "Excellent";
    }

   public void printInfo() {
        System.out.println("ID: " + id + ", Name: " + name + ", Score: " + score + ", Rank: " + getRank());
    }

    private static ArrayList<Studentt> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\nOptions:");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Sort Students by Score");
            System.out.println("6. Search Student by ID");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        editStudent();
                        break;
                    case 3:
                        deleteStudent();
                        break;
                    case 4:
                        displayAllStudents();
                        break;
                    case 5:
                        sortStudentsByScore();
                        break;
                    case 6:
                        searchStudentById();
                        break;
                    case 7:
                        running = false;
                        System.out.println("Exiting program.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
        scanner.close();
    }

   private static void addStudent() {
        try {
            System.out.print("Enter ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
            
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            if (!name.matches("[a-zA-Z\\s]+")) {
                System.out.println("Invalid name. Name must contain only letters and spaces.");
                return;
            }
            
            System.out.print("Enter Score: ");
            double score = scanner.nextDouble();
            if (score < 0 || score > 10) {
                System.out.println("Invalid score. Score must be between 0 and 10.");
                return;
            }

            students.add(new Studentt(id, name, score));
            System.out.println("Student added successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data type.");
            scanner.nextLine(); // Clear invalid input
        }
    }

   private static void editStudent() {
        try {
            System.out.print("Enter the ID of the student to edit: ");
            int id = scanner.nextInt();
            Studentt student = findStudentById(id);
            if (student != null) {
                System.out.print("Enter new Name: ");
                scanner.nextLine(); // Clear buffer
                String name = scanner.nextLine();
                if (!name.matches("[a-zA-Z\\s]+")) {
                    System.out.println("Invalid name. Name must contain only letters and spaces.");
                    return;
                }

                System.out.print("Enter new Score: ");
                double score = scanner.nextDouble();
                if (score < 0 || score > 10) {
                    System.out.println("Invalid score. Score must be between 0 and 10.");
                    return;
                }

                student.setName(name);
                student.setScore(score);
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("Student with ID " + id + " not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data type.");
            scanner.nextLine(); // Clear invalid input
        }
    }

private static void deleteStudent() {
        try {
            System.out.print("Enter the ID of the student to delete: ");
            int id = scanner.nextInt();
            Studentt student = findStudentById(id);
            if (student != null) {
                students.remove(student);
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student with ID " + id + " not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data type.");
            scanner.nextLine(); // Clear invalid input
        }
    }

    private static void displayAllStudents() {
        System.out.println("\nStudent List:");
        for (Studentt student : students) {
            student.printInfo();
        }
    }
    private static void sortStudentsByScore() {
        try {
            System.out.println("Choose sorting order:");
            System.out.println("1. Ascending");
            System.out.println("2. Descending");
            System.out.print("Choose an option: ");
            int orderChoice = scanner.nextInt();

            if (orderChoice == 1) {
                bubbleSortAscending();
                System.out.println("Students sorted by score in ascending order.");
            } else if (orderChoice == 2) {
                bubbleSortDescending();
                System.out.println("Students sorted by score in descending order.");
            } else {
                System.out.println("Invalid choice. Sorting canceled.");
                return;
            }
            displayAllStudents();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
        }
    }
    
private static void bubbleSortAscending() {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = 0; j < students.size() - i - 1; j++) {
                if (students.get(j).getScore() > students.get(j + 1).getScore()) {
                    Studentt temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
    }

    private static void bubbleSortDescending() {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = 0; j < students.size() - i - 1; j++) {
                if (students.get(j).getScore() < students.get(j + 1).getScore()) {
                    Studentt temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
    }
    

    private static void searchStudentById() {
        try {
            System.out.print("Enter the ID of the student to search: ");
            int id = scanner.nextInt();
            Studentt student = findStudentById(id);
            if (student != null) {
                System.out.println("Student found:");
                student.printInfo();
            } else {
                System.out.println("Student with ID " + id + " not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
        }
    }

    private static Studentt findStudentById(int id) {
        for (Studentt student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
}