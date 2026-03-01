import java.util.*;
import java.io.*;

public class CourseManager {
    private static final CourseRegistry registry = new CourseRegistry();
    private static final String FILE_NAME = "courses.txt";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            registry.loadFromFile(FILE_NAME);
        } 
        catch (IOException e) {
            System.out.println("Could not load courses: " + e.getMessage());
        }

        while(true) {
            System.out.println('\n');
            System.out.println("1. Add Graded Course");
            System.out.println("2. Add Pass/Fail Course");
            System.out.println("3. View Courses & GPA");
            System.out.println("4. Edit Course");
            System.out.println("5. Remove Course");
            System.out.println("6. Exit");

            System.out.print("> ");
            String choice = scanner.nextLine().trim();
 
            if (choice.equals("6")) {
                try {
                    registry.saveToFile(FILE_NAME);
                } 
                catch (IOException e) {
                    System.err.println("Save Error: " + e.getMessage());
                }
                break;
            }
 
            executeCommand(choice);
        }
    }

    private static void executeCommand(String choice) {
        if (choice.equals("3")) {
            System.out.println("\n                --- COURSES ---");
            System.out.println("Code |          Name        | Credits | Grade/Pass");
            registry.printCourses();
            System.out.printf("\nGPA: %.2f%n", registry.calculateGPA());
            return;
        }

        if (choice.equals("4")) {
            System.out.print("Enter Course Code to Edit: ");
            String target = scanner.nextLine();
            
            System.out.print("New Type (1 for Graded, 2 for Pass/Fail): ");
            String type = scanner.nextLine();
            System.out.print("New Code: "); 
            String code = scanner.nextLine();
            System.out.print("New Name: "); 
            String name = scanner.nextLine();
            System.out.print("New Credits: "); 
            // I use scanner.nextLine() and parseInt here because if I used scanner.nextInt()
            // it would leave a newline character in the input buffer that would cause issues with
            // subsequent calls to scanner.nextLine()
            int credits = Integer.parseInt(scanner.nextLine());
            
            Course newCourse;
            if (type.equals("1")) {
                System.out.print("Grade (A+, B, C-, etc.): ");
                newCourse = new GradedCourse(code, name, credits, scanner.nextLine());
            } 
            else {
                System.out.print("Passed? (true/false): ");
                newCourse = new PassFailCourse(code, name, credits, Boolean.parseBoolean(scanner.nextLine()));
            }
            
            boolean edited = registry.editCourse(target, newCourse);
            System.out.println(edited ? "Course updated." : "Course not found.");
            return;
        }

        if (choice.equals("5")) {
            System.out.print("Enter Course Code to Remove: ");
            String target = scanner.nextLine();
            boolean removed = registry.removeCourse(target);
            System.out.println(removed ? "Course removed." : "Course not found.");
            return;
        }

        System.out.print("Code: "); 
        String code = scanner.nextLine();
        System.out.print("Name: "); 
        String name = scanner.nextLine();
 
        if (choice.equals("1")) {
            System.out.print("Credits: "); 
            int credits = Integer.parseInt(scanner.nextLine());
            System.out.print("Grade (A+, B, C-, etc.): ");
            registry.addCourse(new GradedCourse(code, name, credits, scanner.nextLine()));
        } 
        else if (choice.equals("2")) {
            System.out.print("Passed? (true/false): ");
            registry.addCourse(new PassFailCourse(code, name, 0, Boolean.parseBoolean(scanner.nextLine())));
        }
    }
}
