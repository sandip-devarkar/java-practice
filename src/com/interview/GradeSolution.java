package com.interview;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

class Student {
    private String id;
    private String name;
    private String grade;

    public Student(String id, String name, String grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getGrade() { return grade; }
}

class Assignment {
    private Student student;
    private String assignmentName;
    private double score;
    private String submissionDate; // e.g. "2026-03-21"
    private String subject;

    public Assignment(Student student, String assignmentName, double score, String submissionDate, String subject) {
        this.student = student;
        this.assignmentName = assignmentName;
        this.score = score;
        this.submissionDate = submissionDate;
        this.subject = subject;
    }

    public Student getStudent() { return student; }
    public double getScore() { return score; }
    public String getSubmissionDate() { return submissionDate; }
    public String getSubject() { return subject; }
}

class GradeBook {
    private List<Assignment> assignments = new ArrayList<>();
    private String className;

    public GradeBook(String className) {
        this.className = className;
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    public int getTotalAssignments() {
        return assignments.size();
    }

    public double getAverageScore() {
        if (assignments.isEmpty()) return 0.0;
        return assignments.stream().mapToDouble(Assignment::getScore).average().orElse(0.0);
    }

    public double getStudentAverage(String studentId) {
        List<Assignment> studentAssignments = assignments.stream()
                .filter(a -> a.getStudent().getId().equals(studentId))
                .collect(Collectors.toList());

        if (studentAssignments.isEmpty()) return 0.0;
        return studentAssignments.stream().mapToDouble(Assignment::getScore).average().orElse(0.0);
    }

    public List<Assignment> getAssignmentsBySubject(String subject) {
        return assignments.stream()
                .filter(a -> a.getSubject().equalsIgnoreCase(subject))
                .collect(Collectors.toList());
    }

    public String getImprovedTrend(String studentId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<Assignment> studentAssignments = assignments.stream()
                .filter(a -> a.getStudent().getId().equals(studentId))
                .sorted(Comparator.comparing(a -> LocalDate.parse(a.getSubmissionDate(), formatter)))
                .collect(Collectors.toList());

        if (studentAssignments.size() < 2) return "Not enough data to determine trend";

        double firstScore = studentAssignments.get(0).getScore();
        double lastScore = studentAssignments.get(studentAssignments.size() - 1).getScore();

        if (lastScore > firstScore) return "Improving";
        else if (lastScore < firstScore) return "Declining";
        else return "Stable";
    }
}

public class GradeSolution {

	public static void main(String[] args) {
        Student s1 = new Student("S1", "Sandip", "10th");

        GradeBook gradeBook = new GradeBook("Math Class");

        gradeBook.addAssignment(new Assignment(s1, "Algebra HW", 70, "2026-03-01", "Math"));
        gradeBook.addAssignment(new Assignment(s1, "Geometry HW", 85, "2026-03-10", "Math"));
        gradeBook.addAssignment(new Assignment(s1, "Trigonometry HW", 90, "2026-03-20", "Math"));

        System.out.println("Total Assignments: " + gradeBook.getTotalAssignments());
        System.out.println("Class Average Score: " + gradeBook.getAverageScore());
        System.out.println("Sandip's Average: " + gradeBook.getStudentAverage("S1"));
        System.out.println("Math Assignments: " + gradeBook.getAssignmentsBySubject("Math").size());
        System.out.println("Trend for Sandip: " + gradeBook.getImprovedTrend("S1"));
    }

}
