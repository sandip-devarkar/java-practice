package com.interview;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.util.*;
import java.util.stream.Collectors;

import java.util.List;

class Exercise {
    private String name;
    private String category;
    private int durationMinutes;
    private double caloriesBurned;

    public Exercise(String name, String category, int durationMinutes, double caloriesBurned) {
        this.name = name;
        this.category = category;
        this.durationMinutes = durationMinutes;
        this.caloriesBurned = caloriesBurned;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public double getCaloriesBurned() {
        return caloriesBurned;
    }

    public String getCategory() {
        return category;
    }
}
class WorkoutSession {
    private List<Exercise> exercises;
    private String date;      // e.g. "2026-03-21"
    private String startTime; // e.g. "07:30"

    public WorkoutSession(List<Exercise> exercises, String date, String startTime) {
        this.exercises = exercises;
        this.date = date;
        this.startTime = startTime;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public String getDate() {
        return date;
    }

    public double getTotalCalories() {
        return exercises.stream().mapToDouble(Exercise::getCaloriesBurned).sum();
    }

    public int getTotalMinutes() {
        return exercises.stream().mapToInt(Exercise::getDurationMinutes).sum();
    }
}

class UserProfile {
    private String userId;
    private String userName;
    private List<WorkoutSession> sessions = new ArrayList<>();

    public UserProfile(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public void addSession(WorkoutSession session) {
        sessions.add(session);
    }

    public int getTotalSessions() {
        return sessions.size();
    }

    public double getTotalCaloriesBurned() {
        return sessions.stream().mapToDouble(WorkoutSession::getTotalCalories).sum();
    }

    public int getTotalWorkoutMinutes() {
        return sessions.stream().mapToInt(WorkoutSession::getTotalMinutes).sum();
    }

    public double getAverageCaloriesPerSession() {
        if (sessions.isEmpty()) return 0.0;
        return getTotalCaloriesBurned() / sessions.size();
    }

    public Map<String, Long> getSessionsByCategory() {
        return sessions.stream()
                .flatMap(s -> s.getExercises().stream())
                .collect(Collectors.groupingBy(Exercise::getCategory, Collectors.counting()));
    }

    public String getWeeklyProgressComparison() {
        if (sessions.isEmpty()) return "No sessions available";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Map<Integer, Double> weekCalories = sessions.stream()
                .collect(Collectors.groupingBy(
                        s -> LocalDate.parse(s.getDate(), formatter).get(IsoFields.WEEK_OF_WEEK_BASED_YEAR),
                        Collectors.summingDouble(WorkoutSession::getTotalCalories)
                ));

        if (weekCalories.size() < 2) return "Not enough data for comparison";

        List<Integer> weeks = new ArrayList<>(weekCalories.keySet());
        Collections.sort(weeks);

        int lastWeek = weeks.get(weeks.size() - 1);
        int prevWeek = weeks.get(weeks.size() - 2);

        double lastCalories = weekCalories.get(lastWeek);
        double prevCalories = weekCalories.get(prevWeek);

        if (prevCalories == 0) return "No data for previous week";

        double change = ((lastCalories - prevCalories) / prevCalories) * 100;
        return String.format("Weekly progress: %.2f%% compared to previous week", change);
    }
}

public class FitnessSolution {

	public static void main(String[] args) {
        Exercise e1 = new Exercise("Pushups", "Strength", 30, 200);
        Exercise e2 = new Exercise("Running", "Cardio", 45, 400);
        Exercise e3 = new Exercise("Yoga", "Flexibility", 60, 150);

        WorkoutSession s1 = new WorkoutSession(Arrays.asList(e1, e2), "2026-03-14", "07:00");
        WorkoutSession s2 = new WorkoutSession(Arrays.asList(e3), "2026-03-21", "08:00");

        UserProfile user = new UserProfile("U1", "Sandip");
        user.addSession(s1);
        user.addSession(s2);

        System.out.println("Total sessions: " + user.getTotalSessions());
        System.out.println("Total calories burned: " + user.getTotalCaloriesBurned());
        System.out.println("Total workout minutes: " + user.getTotalWorkoutMinutes());
        System.out.println("Average calories per session: " + user.getAverageCaloriesPerSession());
        System.out.println("Sessions by category: " + user.getSessionsByCategory());
        System.out.println(user.getWeeklyProgressComparison());
    }

}

