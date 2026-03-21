package com.interview;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class MenuItem {
    private String itemId;
    private String name;
    private String category;
    private double price;

    public MenuItem(String itemId, String name, String category, double price) {
        this.itemId = itemId;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
}
class Order {
    private String orderId;
    private List<MenuItem> items;
    private LocalDateTime orderTime;
    private String orderDate;

    public Order(String orderId, List<MenuItem> items, String orderTime, String orderDate) {
        this.orderId = orderId;
        this.items = items;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.orderTime = LocalDateTime.parse(orderTime, formatter);
        this.orderDate = orderDate;
    }

    public double getTotalValue() {
        return items.stream().mapToDouble(MenuItem::getPrice).sum();
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public List<MenuItem> getItems() {
        return items;
    }
}
public class MenuItemSolution {

	public static void main(String[] args) {
        MenuItem m1 = new MenuItem("101", "Burger", "FastFood", 150);
        MenuItem m2 = new MenuItem("102", "Pizza", "FastFood", 250);
        MenuItem m3 = new MenuItem("103", "Coffee", "Beverage", 100);

        OrderHistory history = new OrderHistory();

        history.addOrder(new Order("O1", Arrays.asList(m1, m2), "2026-03-21 10:15", "2026-03-21"));
        history.addOrder(new Order("O2", Arrays.asList(m3), "2026-03-21 10:45", "2026-03-21"));
        history.addOrder(new Order("O3", Arrays.asList(m1, m3), "2026-03-21 15:30", "2026-03-21"));

        System.out.println("Total Orders: " + history.getTotalOrders());
        System.out.println("Total Revenue: " + history.getTotalRevenue());
        System.out.println("Average Order Value: " + history.getAverageOrderValue());
        System.out.println("Orders by Category: " + history.getOrderCountByCategory());
        System.out.println("Peak Hour: " + history.getPeakHour());
    }

}

class OrderHistory {
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public int getTotalOrders() {
        return orders.size();
    }

    public double getTotalRevenue() {
        return orders.stream().mapToDouble(Order::getTotalValue).sum();
    }

    public double getAverageOrderValue() {
        if (orders.isEmpty()) return 0.0;
        return getTotalRevenue() / orders.size();
    }

    public Map<String, Long> getOrderCountByCategory() {
        return orders.stream()
                .flatMap(o -> o.getItems().stream())
                .collect(Collectors.groupingBy(MenuItem::getCategory, Collectors.counting()));
    }

    public String getPeakHour() {
        if (orders.isEmpty()) return "No orders";

        Map<Integer, Long> hourCounts = orders.stream()
                .collect(Collectors.groupingBy(o -> o.getOrderTime().getHour(), Collectors.counting()));

        return hourCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(e -> "Hour " + e.getKey() + " with " + e.getValue() + " orders")
                .orElse("No orders");
    }
}
