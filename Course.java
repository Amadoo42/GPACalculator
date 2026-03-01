public abstract class Course {
    protected String courseCode;
    protected String courseName;
    protected int credits;

    public Course(String courseCode, String courseName, int credits) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
    }

    public abstract double getScore();
    public abstract int getCredits();
    public abstract String toCSV();
    
    public static Course fromCSV(String line) {
        String[] parts = line.split(",");
        String type = parts[0];
        // There are two types of courses, graded and pass/fail (which have 0 credit hours)
        // Graded will have 4 parameters: course code, course name, credits, and letter grade
        // Pass/fail will have 4 parameters: course code, course name, credits and pass/fail status (true/false)
        if(type.equals("GRADED")) {
            return new GradedCourse(parts[1], parts[2], Integer.parseInt(parts[3]), parts[4]);
        } 
        else if(type.equals("PASSFAIL")) {
            return new PassFailCourse(parts[1], parts[2], Integer.parseInt(parts[3]), Boolean.parseBoolean(parts[4]));
        }
        // Throw exception in case of invalid course type
        throw new IllegalArgumentException("Invalid course type: " + type);
    }
}


