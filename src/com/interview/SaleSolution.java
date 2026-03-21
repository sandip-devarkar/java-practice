package com.interview;


import java.util.*;
import java.util.stream.Collectors;


class Product {
    private String productId;
    private String name;
    private String category;
    private double price;

    public Product(String productId, String name, String category, double price) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getProductId() { return productId; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
}
class Purchase {
    private Product product;
    private int quantity;
    private String customerId;
    private String purchaseDate;

    public Purchase(Product product, int quantity, String customerId, String purchaseDate) {
        this.product = product;
        this.quantity = quantity;
        this.customerId = customerId;
        this.purchaseDate = purchaseDate;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public String getCustomerId() { return customerId; }
    public String getPurchaseDate() { return purchaseDate; }

    public double getTotalValue() {
        return product.getPrice() * quantity;
    }
}

class SalesAnalyzer {
    private List<Purchase> purchases = new ArrayList<>();

    public void addPurchase(Purchase purchase) {
        purchases.add(purchase);
    }

    public int getTotalPurchases() {
        return purchases.size();
    }

    public double getTotalRevenue() {
        return purchases.stream().mapToDouble(Purchase::getTotalValue).sum();
    }

    public int getTotalItemsSold() {
        return purchases.stream().mapToInt(Purchase::getQuantity).sum();
    }

    public double getAveragePurchaseValue() {
        if (purchases.isEmpty()) return 0.0;
        return getTotalRevenue() / purchases.size();
    }

    public long getUniqueCustomers() {
        return purchases.stream().map(Purchase::getCustomerId).distinct().count();
    }

    public String getTopSellingProduct() {
        if (purchases.isEmpty()) return "No purchases";

        Map<Product, Integer> productQuantities = purchases.stream()
                .collect(Collectors.groupingBy(Purchase::getProduct,
                        Collectors.summingInt(Purchase::getQuantity)));

        return productQuantities.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(e -> e.getKey().getName() + " sold " + e.getValue() + " units")
                .orElse("No purchases");
    }
}

public class SaleSolution {

	public static void main(String[] args) {
        Product p1 = new Product("P1", "Laptop", "Electronics", 50000);
        Product p2 = new Product("P2", "Phone", "Electronics", 30000);
        Product p3 = new Product("P3", "Shoes", "Fashion", 2000);

        SalesAnalyzer analyzer = new SalesAnalyzer();

        analyzer.addPurchase(new Purchase(p1, 2, "C1", "2026-03-20"));
        analyzer.addPurchase(new Purchase(p2, 3, "C2", "2026-03-21"));
        analyzer.addPurchase(new Purchase(p3, 5, "C1", "2026-03-21"));

        System.out.println("Total Purchases: " + analyzer.getTotalPurchases());
        System.out.println("Total Revenue: " + analyzer.getTotalRevenue());
        System.out.println("Total Items Sold: " + analyzer.getTotalItemsSold());
        System.out.println("Average Purchase Value: " + analyzer.getAveragePurchaseValue());
        System.out.println("Unique Customers: " + analyzer.getUniqueCustomers());
        System.out.println("Top Selling Product: " + analyzer.getTopSellingProduct());
    }

}
