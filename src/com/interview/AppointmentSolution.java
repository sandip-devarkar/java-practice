package com.interview;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

// Patient class
class Patient {
    private final int id;
    private final String name;
    private final String bloodType;
    private final int age;

    public Patient(int id, String name, String bloodType, int age) {
        this.id = id;
        this.name = name;
        this.bloodType = bloodType;
        this.age = age;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getBloodType() { return bloodType; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", age=" + age +
                '}';
    }
}

// Appointment class
class Appointment {
    private final Patient patient;
    private final String doctorName;
    private final LocalDate appointmentDate;
    private final LocalTime appointmentTime;
    private final String appointmentType;
    private final int durationInMinutes;

    public Appointment(Patient patient, String doctorName, LocalDate appointmentDate,
                       LocalTime appointmentTime, String appointmentType, int durationInMinutes) {
        this.patient = patient;
        this.doctorName = doctorName;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.appointmentType = appointmentType;
        this.durationInMinutes = durationInMinutes;
    }

    public Patient getPatient() { return patient; }
    public String getDoctorName() { return doctorName; }
    public LocalDate getAppointmentDate() { return appointmentDate; }
    public LocalTime getAppointmentTime() { return appointmentTime; }
    public String getAppointmentType() { return appointmentType; }
    public int getDurationInMinutes() { return durationInMinutes; }

    @Override
    public String toString() {
        return "Appointment{" +
                "patient=" + patient.getName() +
                ", doctorName='" + doctorName + '\'' +
                ", date=" + appointmentDate +
                ", time=" + appointmentTime +
                ", type='" + appointmentType + '\'' +
                ", duration=" + durationInMinutes +
                " mins}";
    }
}

// ClinicScheduler class
class ClinicScheduler {
    private final String clinicName;
    private final List<Appointment> appointments = new ArrayList<>();

    public ClinicScheduler(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getClinicName() { return clinicName; }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public int getTotalAppointments() {
        return appointments.size();
    }

    public List<Appointment> getAppointmentsByType(String type) {
        return appointments.stream()
                .filter(appt -> appt.getAppointmentType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public List<Appointment> getAppointmentsForDoctor(String doctorName) {
        return appointments.stream()
                .filter(appt -> appt.getDoctorName().equalsIgnoreCase(doctorName))
                .collect(Collectors.toList());
    }

    public List<Appointment> getAppointmentsOnDate(LocalDate date) {
        return appointments.stream()
                .filter(appt -> appt.getAppointmentDate().equals(date))
                .collect(Collectors.toList());
    }

    // Fixed bug: avoid divide by zero
    public double getAverageDuration() {
        int count = appointments.size();
        if (count == 0) return 0.0;
        double total = appointments.stream()
                .mapToDouble(Appointment::getDurationInMinutes)
                .sum();
        return total / count;
    }

    // Find busiest doctor (most appointments)
    public String getBusiestDoctor() {
        if (appointments.isEmpty()) return "No appointments scheduled";

        Map<String, Long> doctorCounts = appointments.stream()
                .collect(Collectors.groupingBy(Appointment::getDoctorName, Collectors.counting()));

        return Collections.max(doctorCounts.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
    
    public String getBusiestDoctor2() {
        return appointments.stream()
            .collect(Collectors.groupingBy(Appointment::getDoctorName, Collectors.counting()))
            .entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse("No appointments scheduled");
    }
}

// Solution class (main)
public class AppointmentSolution {
    public static void main(String[] args) {
        ClinicScheduler scheduler = new ClinicScheduler("City Health Clinic");

        Patient p1 = new Patient(1, "Alice", "O+", 30);
        Patient p2 = new Patient(2, "Bob", "A-", 45);
        Patient p3 = new Patient(3, "Charlie", "B+", 28);

        scheduler.addAppointment(new Appointment(p1, "Dr. Smith", LocalDate.now(),
                LocalTime.of(10, 0), "Checkup", 30));
        scheduler.addAppointment(new Appointment(p2, "Dr. Smith", LocalDate.now(),
                LocalTime.of(11, 0), "Dental", 45));
        scheduler.addAppointment(new Appointment(p3, "Dr. Adams", LocalDate.now(),
                LocalTime.of(12, 0), "Eye Exam", 20));

        // Reports
        System.out.println("Clinic: " + scheduler.getClinicName());
        System.out.println("Total Appointments: " + scheduler.getTotalAppointments());
        System.out.println("Average Duration: " + scheduler.getAverageDuration());
        System.out.println("Appointments for Dr. Smith: " + scheduler.getAppointmentsForDoctor("Dr. Smith"));
        System.out.println("Appointments today: " + scheduler.getAppointmentsOnDate(LocalDate.now()));
        System.out.println("Busiest Doctor: " + scheduler.getBusiestDoctor());
    }
}