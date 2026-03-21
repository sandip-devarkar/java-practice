package com.interview;


import java.util.*;
import java.util.stream.Collectors;


class Course {
    private String courseId;
    private String courseName;
    private String category;
    private int credits;

    public Course(String courseId, String courseName, String category, int credits) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.category = category;
        this.credits = credits;
    }

    public String getCourseId() { return courseId; }
    public String getCourseName() { return courseName; }
    public String getCategory() { return category; }
    public int getCredits() { return credits; }
}

class Enrollment {
    private String studentId;
    private Course course;
    private String enrollmentDate;
    private double grade; // numeric grade, e.g. 0–100

    public Enrollment(String studentId, Course course, String enrollmentDate, double grade) {
        this.studentId = studentId;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.grade = grade;
    }

    public String getStudentId() { return studentId; }
    public Course getCourse() { return course; }
    public String getEnrollmentDate() { return enrollmentDate; }
    public double getGrade() { return grade; }
}

class CourseSystem {
    private List<Enrollment> enrollments = new ArrayList<>();
    private String systemName;

    public CourseSystem(String systemName) {
        this.systemName = systemName;
    }

    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public int getTotalEnrollments() {
        return enrollments.size();
    }

    public double getAverageGrade() {
        if (enrollments.isEmpty()) return 0.0;
        return enrollments.stream().mapToDouble(Enrollment::getGrade).average().orElse(0.0);
    }

    public double getStudentAverage(String studentId) {
        List<Enrollment> studentEnrollments = enrollments.stream()
                .filter(e -> e.getStudentId().equals(studentId))
                .collect(Collectors.toList());

        if (studentEnrollments.isEmpty()) return 0.0;
        return studentEnrollments.stream().mapToDouble(Enrollment::getGrade).average().orElse(0.0);
    }

    public Map<String, Long> getEnrollmentsByCategory() {
        return enrollments.stream()
                .map(e -> e.getCourse().getCategory())
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }

    public String getImprovementTrend(String studentId) {
        List<Enrollment> studentEnrollments = enrollments.stream()
                .filter(e -> e.getStudentId().equals(studentId))
                .sorted(Comparator.comparing(Enrollment::getEnrollmentDate))
                .collect(Collectors.toList());

        if (studentEnrollments.size() < 2) return "Not enough data to determine trend";

        double firstGrade = studentEnrollments.get(0).getGrade();
        double lastGrade = studentEnrollments.get(studentEnrollments.size() - 1).getGrade();

        if (lastGrade > firstGrade) return "Improving";
        else if (lastGrade < firstGrade) return "Declining";
        else return "Stable";
    }
}

public class CourseSolution {

	 public static void main(String[] args) {
	        Course c1 = new Course("C101", "Java Programming", "Computer Science", 4);
	        Course c2 = new Course("C102", "Data Structures", "Computer Science", 3);
	        Course c3 = new Course("C201", "History of India", "Humanities", 2);

	        CourseSystem system = new CourseSystem("University Course System");

	        system.addEnrollment(new Enrollment("S1", c1, "2026-01-10", 75));
	        system.addEnrollment(new Enrollment("S1", c2, "2026-02-15", 85));
	        system.addEnrollment(new Enrollment("S2", c3, "2026-03-01", 65));

	        System.out.println("Total Enrollments: " + system.getTotalEnrollments());
	        System.out.println("Average Grade: " + system.getAverageGrade());
	        System.out.println("S1 Average: " + system.getStudentAverage("S1"));
	        System.out.println("Enrollments by Category: " + system.getEnrollmentsByCategory());
	        System.out.println("Trend for S1: " + system.getImprovementTrend("S1"));
	    }

}
