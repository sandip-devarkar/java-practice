package com.interview;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import java.util.*;
import java.util.stream.Collectors;

class ParkingLot {
    private String lotName;
    private List<ParkingSession> sessions = new ArrayList<>();

    public ParkingLot(String lotName) {
        this.lotName = lotName;
    }

    public void addSession(ParkingSession session) {
        sessions.add(session);
    }

    public int getTotalSessions() {
        return sessions.size();
    }

    public long getCurrentlyParkingCount() {
        return sessions.stream().filter(s -> !s.isCompleted()).count();
    }

    public double getTotalRevenue() {
        return sessions.stream().mapToDouble(ParkingSession::getFee).sum();
    }

    public double getAverageParkingDuration() {
        List<Long> durations = sessions.stream()
                .filter(ParkingSession::isCompleted)
                .map(ParkingSession::getDurationInMinutes)
                .collect(Collectors.toList());

        if (durations.isEmpty()) return 0.0;
        return durations.stream().mapToLong(Long::longValue).average().orElse(0.0);
    }

    public String[] getLongestParkingSession() {
        return sessions.stream()
                .filter(ParkingSession::isCompleted)
                .max(Comparator.comparingLong(ParkingSession::getDurationInMinutes))
                .map(s -> new String[]{s.getVehicle().getLicencePlate(),
                        String.valueOf(s.getDurationInMinutes())})
                .orElse(new String[]{"No completed sessions", "0"});
    }
}

class ParkingSession {
    private Vehicle vehicle;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime; // null if still parked
    private String date;
    private double hourlyRate;

    public ParkingSession(Vehicle vehicle, String entryTime, String exitTime, String date, double hourlyRate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.vehicle = vehicle;
        this.entryTime = LocalDateTime.parse(entryTime, formatter);
        this.exitTime = (exitTime == null ? null : LocalDateTime.parse(exitTime, formatter));
        this.date = date;
        this.hourlyRate = hourlyRate;
    }

    public boolean isCompleted() {
        return exitTime != null;
    }

    public long getDurationInMinutes() {
        if (exitTime == null) return 0;
        return ChronoUnit.MINUTES.between(entryTime, exitTime);
    }

    public double getFee() {
        if (exitTime == null) return 0.0;
        double hours = getDurationInMinutes() / 60.0;
        return hours * hourlyRate;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
class Vehicle {
    private String licencePlate;
    private String vehicleType;
    private String color;

    public Vehicle(String licencePlate, String vehicleType, String color) {
        this.licencePlate = licencePlate;
        this.vehicleType = vehicleType;
        this.color = color;
    }

    public String getLicencePlate() {
        return licencePlate;
    }
}
public class ParkingSolution {

	   public static void main(String[] args) {
	        Vehicle v1 = new Vehicle("MH12AB1234", "Car", "Red");
	        Vehicle v2 = new Vehicle("MH14XY5678", "Bike", "Black");

	        ParkingLot lot = new ParkingLot("City Center Lot");

	        lot.addSession(new ParkingSession(v1, "2026-03-21 10:00", "2026-03-21 12:30", "2026-03-21", 50));
	        lot.addSession(new ParkingSession(v2, "2026-03-21 11:00", null, "2026-03-21", 30));

	        System.out.println("Total sessions: " + lot.getTotalSessions());
	        System.out.println("Currently parking: " + lot.getCurrentlyParkingCount());
	        System.out.println("Total revenue: " + lot.getTotalRevenue());
	        System.out.println("Average duration: " + lot.getAverageParkingDuration());
	        String[] longest = lot.getLongestParkingSession();
	        System.out.println("Longest session: Licence " + longest[0] + ", Duration " + longest[1] + " minutes");
	    }

}
