//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.awt.desktop.OpenURIEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of students
        System.out.print("Enter the number of students: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            input();
        }

        // Display the menu
        while (true) {
            System.out.println("\n------ MENU ------");
            System.out.println("1. Display the student list");
            System.out.println("2. Remove a student by code");
            System.out.println("3. Sort students by descending grades");
            System.out.println("4. Search for a student by code or name");
            System.out.println("5. Find students with grades >= x");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    output();
                    break;
                case 2:
                    System.out.print("Enter the student code to remove: ");
                    String code = scanner.nextLine();
                    removeByCode(code);
                    break;
                case 3:
                    sortByGradeDesc();
                    System.out.println("Students have been sorted by descending grades.");
                    break;
                case 4:
                    System.out.print("Enter the student code or name to search: ");
                    String keyword = scanner.nextLine();
                    Student foundStudent = findByCodeOrName(keyword);
                    if (foundStudent != null) {
                        System.out.println(foundStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter the grade threshold x: ");
                    float x = scanner.nextFloat();
                    List<Student> filteredStudents = filterByGrade(x);
                    if (filteredStudents.isEmpty()) {
                        System.out.println("No students have grades >= " + x);
                    } else {
                        System.out.println("Students with grades >= " + x + ":");
                        for (Student student : filteredStudents) {
                            System.out.println(student);
                        }
                    }
                    break;
                case 6:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please choose again.");
            }
        }
    }

    // Input student information
    public static void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter student information:");

        System.out.print("Enter student code: ");
        String code = scanner.nextLine();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student grade: ");
        float grade = scanner.nextFloat();
        scanner.nextLine(); // Consume the newline after entering the grade

        Student student = new Student(code, name, grade);
        studentList.add(student);
    }

    // Display the list of students
    public static void output() {
        if (studentList.isEmpty()) {
            System.out.println("The student list is empty.");
        } else {
            System.out.println("Student List:");
            for (Student student : studentList) {
                System.out.println(student);
            }
        }
    }

    // Remove a student by code
    public static void removeByCode(String code) {
        Student studentToRemove = null;
        for (Student student : studentList) {
            if (student.getCode().equalsIgnoreCase(code)) {
                studentToRemove = student;
                break;
            }
        }

        if (studentToRemove != null) {
            studentList.remove(studentToRemove);
            System.out.println("Student with code " + code + " has been removed.");
        } else {
            System.out.println("No student found with code " + code);
        }
    }
    public static void sortByGradeDesc() {
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Float.compare(s2.getGrade(), s1.getGrade());
            }
        });
    }
    // Search for a student by code or name
    public static Student findByCodeOrName(String keyword) {
        for (Student student : studentList) {
            if (student.getCode().equalsIgnoreCase(keyword) || student.getName().equalsIgnoreCase(keyword)) {
                return student;
            }
        }
        return null;
    }
    // Find students with grades >= x
    public static List<Student> filterByGrade(float x) {
        List<Student> filteredList = new ArrayList<>();
        for (Student student : studentList) {
            if (student.getGrade() >= x) {
                filteredList.add(student);
            }
        }
        return filteredList;
    }
}

class Student {
    private String code;
    private String name;
    private float grade;

    public Student(String code, String name, float grade) {
        this.code = code;
        this.name = name;
        this.grade = grade;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public float getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}
