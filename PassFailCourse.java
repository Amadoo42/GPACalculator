public class PassFailCourse extends Course {
    private boolean passed;

    public PassFailCourse(String courseCode, String courseName, int credits, boolean passed) {
        super(courseCode, courseName, credits);
        this.passed = passed;
    }

    @Override
    public double getScore() {
        return 0.0; // Pass/fail courses do not contribute to GPA
    }

    @Override
    public int getCredits() {
        return 0; // Pass/fail courses do not contribute to credit hours
    }

    @Override
    public String toCSV() {
        return "PASSFAIL," + courseCode + "," + courseName + "," + credits + "," + passed;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-25s %-5d %-5s", courseCode, courseName, credits, passed ? "PASS" : "FAIL");
    }
}