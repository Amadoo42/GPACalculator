public class GradedCourse extends Course {
    private String letterGrade;

    public GradedCourse(String courseCode, String courseName, int credits, String letterGrade) {
        super(courseCode, courseName, credits);
        this.letterGrade = letterGrade;
    }

    @Override
    public double getScore() {
        switch (letterGrade) {
            case "A+": return 4.0;
            case "A": return 3.7;
            case "A-": return 3.4;
            case "B+": return 3.2;
            case "B": return 3.0;
            case "B-": return 2.8;
            case "C+": return 2.6;
            case "C": return 2.4;
            case "C-": return 2.2;
            case "D+": return 2.0;
            case "D": return 1.5;
            case "D-": return 1.0;
            case "F": return 0.0;
            // If we don't recognize the letter grade, throw an exception
            default: throw new IllegalArgumentException("Invalid letter grade: " + letterGrade);
        }
    }

    @Override
    public int getCredits() {
        return credits;
    }

    @Override
    public String toCSV() {
        return "GRADED," + courseCode + "," + courseName + "," + credits + "," + letterGrade;
    }

    /*
    This basically formats the course information in a nice way for display. 
    The %-10s means left-align the string and use at least 10 characters, 
    %-25s means left-align the string and use at least 25 characters, 
    and %-5d means left-align the integer and use at least 5 characters. 
    This way, all the course information will be aligned in columns when printed.
    CS101      Introduction to CS        3     A    
    MATH201    Calculus II               4     B+
    */
    @Override
    public String toString() {
        return String.format("%-10s %-25s %-5d %-5s", courseCode, courseName, credits, letterGrade);
    }
}