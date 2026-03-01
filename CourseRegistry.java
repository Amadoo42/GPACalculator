import java.nio.file.*;
import java.util.*;
import java.io.*;

public class CourseRegistry {
    private final List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
    }

    public boolean removeCourse(String code) {
        for(int i = 0; i < courses.size(); i++) {
            if(courses.get(i).courseCode.equalsIgnoreCase(code)) {
                courses.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean editCourse(String code, Course newCourse) {
        for(int i = 0; i < courses.size(); i++) {
            if(courses.get(i).courseCode.equalsIgnoreCase(code)) {
                courses.set(i, newCourse);
                return true;
            }
        }
        return false;
    }

    public double calculateGPA() {
        double totalPoints = 0;
        int totalCredits = 0;

        for (Course course : courses) {
            totalPoints += course.getScore() * course.getCredits();
            totalCredits += course.getCredits();
        }
        return totalCredits == 0 ? 0.0 : totalPoints / totalCredits;
    }

    public void printCourses() {
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    public void saveToFile(String filename) throws IOException {
        // Here we try to open a BufferedWriter to the specified file and write each course in CSV format
        // If it fails to open the file, it will throw an IOException which we declare in the method signature
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
            for (Course course : courses) {
                writer.write(course.toCSV());
                writer.newLine(); 
            }
        }
    }

    public void loadFromFile(String filename) throws IOException {
        // Here we try to open a BufferedReader to the specified file and read each line, converting it to a Course object
        // If it fails to open the file, it will throw an IOException which we declare in the method signature
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                courses.add(Course.fromCSV(line));
            }
        }
    }
}
