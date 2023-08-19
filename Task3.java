import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class Student 
{
    private String id;
    private String name;
    private int age;
    public Student(String id, String name, int age) 
    {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    public String getId()
    {
        return id;
    }
    public String getName() 
    {
        return name;
    }
    public int getAge() 
    {
        return age;
    }
    public void setName(String name) 
    {
        this.name = name;
    }
    public void setAge(int age) 
    {
        this.age = age;
    }
}
public class Task3 
{
    public static String FILE_PATH = "students.txt";
    private List<Student> students;
    private Scanner scanner;
    public Task3() 
    {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    public static void main(String[] args) 
    {
        Task3 system = new Task3();
        system.loadStudentsFromFile();
        system.run();
        system.saveStudentsToFile();
    }
    private void run() 
    {
        boolean exit = false;
        while (!exit) 
        {
            System.out.println("Student Management System");
            System.out.println("1. Add a new student");
            System.out.println("2. Edit student information");
            System.out.println("3. Search for a student");
            System.out.println("4. Display all students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) 
            {
                case 1:
                    addStudent();
                    break;
                case 2:
                    editStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");
            }
        }
    }
    private void addStudent()
    {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); 
        Student student = new Student(id, name, age);
        students.add(student);
        System.out.println("Student added successfully!");
    }
    private void editStudent()
    {
        System.out.print("Enter student ID to edit: ");
        String idToEdit = scanner.nextLine();
        boolean found = false;
        for (Student student : students) {
            if (student.getId().equals(idToEdit)) {
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                System.out.print("Enter new age: ");
                int newAge = scanner.nextInt();
                //scanner.nextLine(); 
                student.setName(newName);
                student.setAge(newAge);
                System.out.println("Student information updated successfully!");
                found = true;
                break;
            }
        }
        if (!found) 
        {
            System.out.println("Student with ID '" + idToEdit + "' not found.");
        }
    }
    private void searchStudent() 
    {
        System.out.print("Enter student ID to search: ");
        String idToSearch = scanner.nextLine();
        boolean found = false;
        for (Student student : students) {
            if (student.getId().equals(idToSearch)) {
                System.out.println("Student Found:");
                System.out.println("ID: " + student.getId());
                System.out.println("Name: " + student.getName());
                System.out.println("Age: " + student.getAge());
                found = true;
                break;
            }
        }
        if (!found) 
        {
            System.out.println("Student with ID '" + idToSearch + "' not found.");
        }
    }
    private void displayAllStudents() 
    {
        if (students.isEmpty()) 
        {
            System.out.println("No students found.");
        } 
        else 
        {
            System.out.println("All Students:");
            for (Student student : students) {
                System.out.println("ID: " + student.getId() + ", Name: " + student.getName() + ", Age: " + student.getAge());
            }
        }
    }
    private void loadStudentsFromFile() 
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] data = line.split(",");
                String id = data[0].trim();
                String name = data[1].trim();
                int age = Integer.parseInt(data[2].trim());
                students.add(new Student(id, name, age));
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Failed to load student data from file. Starting with an empty student list.");
        }
    }
    private void saveStudentsToFile() 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) 
        {
            for (Student student : students) 
            {
                writer.write(student.getId() + "," + student.getName() + "," + student.getAge() + "\n");
            }
            System.out.println("Student data saved to file.");
        } 
        catch (IOException e) 
        {
            System.out.println("Failed to save student data to file.");
        }
    }
}