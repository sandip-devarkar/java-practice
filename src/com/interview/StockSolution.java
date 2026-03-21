package com.interview;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Stock class
class Stock {
    private final String symbol;
    private final String name;

    public Stock(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

// PriceRecord class
class PriceRecord {
    private final Stock stock;
    private final double price;
    private final LocalDate date;

    public PriceRecord(Stock stock, double price, LocalDate date) {
        this.stock = stock;
        this.price = price;
        this.date = date;
    }

    public Stock getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "PriceRecord{" +
                "stock=" + stock.getSymbol() +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}

// StockCollection class
class StockCollection {
    private final List<PriceRecord> priceRecords = new ArrayList<>();

    // Check if collection contains records for a given stock
    public boolean contains(Stock stock) {
        return priceRecords.stream()
                .anyMatch(pr -> pr.getStock().equals(stock));
    }

    // Number of price records
    public int getNumPriceRecords() {
        return priceRecords.size();
    }

    // Add a new price record
    public void addPriceRecord(PriceRecord record) {
        priceRecords.add(record);
    }

    // Max price
    public double getMaxPrice() {
        return priceRecords.stream()
                .mapToDouble(PriceRecord::getPrice)
                .max()
                .orElse(0.0); // safe default if no records
    }

    // Min price
    public double getMinPrice() {
        return priceRecords.stream()
                .mapToDouble(PriceRecord::getPrice)
                .min()
                .orElse(0.0); // safe default if no records
    }

    // Average price (fixed bug: avoid divide by zero)
    public double getAvgPrice() {
        int count = priceRecords.size();
        if (count == 0) return 0.0; // avoid division by zero
        double total = priceRecords.stream()
                .mapToDouble(PriceRecord::getPrice)
                .sum();
        return total / count;
    }
}

// Solution class (main)
public class StockSolution {
    public static void main(String[] args) {
        Stock stock1 = new Stock("AAPL", "Apple Inc.");
        Stock stock2 = new Stock("MSFT", "Microsoft Corp.");

        StockCollection collection = new StockCollection();

        // Add price records
        collection.addPriceRecord(new PriceRecord(stock1, 150.5, LocalDate.now().minusDays(2)));
        collection.addPriceRecord(new PriceRecord(stock1, 155.2, LocalDate.now().minusDays(1)));
        collection.addPriceRecord(new PriceRecord(stock2, 300.0, LocalDate.now()));

        // Reports
        System.out.println("Contains Apple? " + collection.contains(stock1));
        System.out.println("Number of Price Records: " + collection.getNumPriceRecords());
        System.out.println("Max Price: " + collection.getMaxPrice());
        System.out.println("Min Price: " + collection.getMinPrice());
        System.out.println("Average Price: " + collection.getAvgPrice());
    }
}
