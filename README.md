# Java GPA Calculator

A CLI application to manage academic courses and calculate cumulative GPA.

## Features
- **Add Courses**: Supports both Graded (A-F) and Pass/Fail types.
- **GPA Calculation**: Automatically calculates GPA based on weighted credit hours for graded courses.
- **Persistence**: Saves and loads your course list from a `courses.txt` file.
- **Management**: Edit or remove existing courses by their course code.

## How to Run
1. Compile the source files:
 ```bash
 javac *.java
  ```

2. Run the manager:
```bash
java CourseManager
```



## File Structure

* `Course.java`: Abstract base class for course logic.
* `GradedCourse.java` / `PassFailCourse.java`: Specific course implementations.
* `CourseRegistry.java`: Handles the collection and calculations.
* `CourseManager.java`: The main CLI entry point.
