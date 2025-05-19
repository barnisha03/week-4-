import java.util.*;

// --- Abstract Base Class for Course Type ---
abstract class CourseType {
    private String instructor;

    public CourseType(String instructor) {
        this.instructor = instructor;
    }

    public String getInstructor() {
        return instructor;
    }

    public abstract String getEvaluationMethod();

    @Override
    public String toString() {
        return getClass().getSimpleName() + " by " + instructor + " | Evaluation: " + getEvaluationMethod();
    }
}

// --- Concrete Subclasses for Course Types ---
class ExamCourse extends CourseType {
    public ExamCourse(String instructor) {
        super(instructor);
    }

    @Override
    public String getEvaluationMethod() {
        return "Written Exams";
    }
}

class AssignmentCourse extends CourseType {
    public AssignmentCourse(String instructor) {
        super(instructor);
    }

    @Override
    public String getEvaluationMethod() {
        return "Assignments and Projects";
    }
}

class ResearchCourse extends CourseType {
    public ResearchCourse(String instructor) {
        super(instructor);
    }

    @Override
    public String getEvaluationMethod() {
        return "Research Papers and Presentations";
    }
}

// --- Generic Course Wrapper Class ---
class Course<T extends CourseType> {
    private String courseName;
    private T courseType;

    public Course(String courseName, T courseType) {
        this.courseName = courseName;
        this.courseType = courseType;
    }

    public String getCourseName() {
        return courseName;
    }

    public T getCourseType() {
        return courseType;
    }

    @Override
    public String toString() {
        return "Course: " + courseName + " | " + courseType.toString();
    }
}

// --- Utility Class ---
class CourseUtility {
    public static void displayCourses(List<? extends Course<? extends CourseType>> courses) {
        for (Course<? extends CourseType> course : courses) {
            System.out.println(course);
        }
    }
}

// --- Main Class to Test the System ---
public class UniversityCourseManagementSystem {
    public static void main(String[] args) {
        // Create different types of courses
        Course<ExamCourse> math101 = new Course<>("Math 101", new ExamCourse("Dr. Sharma"));
        Course<AssignmentCourse> cs102 = new Course<>("Intro to Java", new AssignmentCourse("Prof. Basha"));
        Course<ResearchCourse> ai501 = new Course<>("AI Capstone", new ResearchCourse("Dr. Sen"));

        // Add to a course list using wildcard
        List<Course<? extends CourseType>> allCourses = new ArrayList<>();
        allCourses.add(math101);
        allCourses.add(cs102);
        allCourses.add(ai501);

        // Display all courses
        System.out.println("=== University Course Catalog ===");
        CourseUtility.displayCourses(allCourses);
    }
}


