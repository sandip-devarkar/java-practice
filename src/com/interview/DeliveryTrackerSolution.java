package com.interview;

import java.util.*;
import java.util.stream.Collectors;

class Package {
    private String trackingNumber;
    private String senderAddress;
    private String recipientAddress;
     double weight; //The field Package.weight is not visible
                            // change visibilty to package
    private String packageType;

    public Package(String trackingNumber, String senderAddress, String recipientAddress, double weight, String packageType) {
        this.trackingNumber = trackingNumber;
        this.senderAddress = senderAddress;
        this.recipientAddress = recipientAddress;
        this.weight = weight;
        this.packageType = packageType;
    }

    public String getTrackingNumber() { return trackingNumber; }
}
class DeliveryRecord {
    private Package pkg;
    private String driverName;
    private String shipDate;
    private String expectedDeliveryDate;
    private String actualDeliveryDate;
    private String status; // e.g. "Delivered", "Pending"

    public DeliveryRecord(Package pkg, String driverName, String shipDate, String expectedDeliveryDate, String actualDeliveryDate, String status) {
        this.pkg = pkg;
        this.driverName = driverName;
        this.shipDate = shipDate;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.actualDeliveryDate = actualDeliveryDate;
        this.status = status;
    }

    public Package getPackage() { return pkg; }
    public String getDriverName() { return driverName; }
    public String getShipDate() { return shipDate; }
    public String getExpectedDeliveryDate() { return expectedDeliveryDate; }
    public String getActualDeliveryDate() { return actualDeliveryDate; }
    public String getStatus() { return status; }

    public boolean isDeliveredOnTime() {
        if (actualDeliveryDate == null || expectedDeliveryDate == null) return false;
        return actualDeliveryDate.compareTo(expectedDeliveryDate) <= 0;
    }
}

class DeliveryTracker {
    private List<DeliveryRecord> deliveries = new ArrayList<>();

    public void addDelivery(DeliveryRecord record) {
        deliveries.add(record);
    }

    public int getTotalDeliveries() {
        return deliveries.size();
    }

    public List<DeliveryRecord> getDeliveriesByDate(String date) {
        return deliveries.stream()
                .filter(d -> d.getShipDate().equals(date))
                .collect(Collectors.toList());
    }

    public List<DeliveryRecord> getDeliveriesByDriver(String driverName) {
        return deliveries.stream()
                .filter(d -> d.getDriverName().equals(driverName))
                .collect(Collectors.toList());
    }

    public double getAveragePackageWeight() {
        if (deliveries.isEmpty()) return 0.0;
        return deliveries.stream()
                .mapToDouble(d -> d.getPackage().weight) 
                .average()
                .orElse(0.0);
    }

    public double getOnTimeDeliveryRate() {
        if (deliveries.isEmpty()) return 0.0;
        long onTimeCount = deliveries.stream().filter(DeliveryRecord::isDeliveredOnTime).count();
        return (onTimeCount * 100.0) / deliveries.size();
    }
}

public class DeliveryTrackerSolution {

	
	public static void main(String[] args) {
        Package p1 = new Package("T001", "Pune", "Mumbai", 5.0, "Standard");
        Package p2 = new Package("T002", "Delhi", "Bangalore", 10.0, "Express");

        DeliveryTracker tracker = new DeliveryTracker();

        tracker.addDelivery(new DeliveryRecord(p1, "Driver A", "2026-03-20", "2026-03-22", "2026-03-21", "Delivered"));
        tracker.addDelivery(new DeliveryRecord(p2, "Driver B", "2026-03-20", "2026-03-23", "2026-03-24", "Delivered"));

        System.out.println("Total Deliveries: " + tracker.getTotalDeliveries());
        System.out.println("Deliveries by Driver A: " + tracker.getDeliveriesByDriver("Driver A").size());
        System.out.println("Average Package Weight: " + tracker.getAveragePackageWeight());
        System.out.println("On-Time Delivery Rate: " + tracker.getOnTimeDeliveryRate() + "%");
    }

}
