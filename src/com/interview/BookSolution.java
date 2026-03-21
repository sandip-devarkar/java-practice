package com.interview;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

class Book {
    private String isbn;
    private String title;
    private String author;
    private String genre;

    public Book(String isbn, String title, String author, String genre) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }
}

class BorrowRecord {
    private Book book;
    private LocalDate borrowDate;
    private LocalDate returnDate; // null if not returned yet

    public BorrowRecord(Book book, LocalDate borrowDate, LocalDate returnDate) {
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public boolean isReturned() {
        return returnDate != null;
    }

    public long getHoldingDays() {
        if (isReturned()) {
            return ChronoUnit.DAYS.between(borrowDate, returnDate);
        }
        return 0;
    }
}

class MemberAccount {
    private List<BorrowRecord> records = new ArrayList<>();

    public void addBorrowRecord(BorrowRecord record) {
        records.add(record);
    }

    public int getTotalBorrowedBooks() {
        return records.size();
    }

    public long getCurrentBorrowedCount() {
        return records.stream().filter(r -> !r.isReturned()).count();
    }

    public long getReturnedCount() {
        return records.stream().filter(BorrowRecord::isReturned).count();
    }

    public double getAverageHoldingDays() {
        List<Long> holdingDays = records.stream()
                .filter(BorrowRecord::isReturned)
                .map(BorrowRecord::getHoldingDays)
                .collect(Collectors.toList());

        if (holdingDays.isEmpty()) return 0.0; // ✅ handle no returned books

        return holdingDays.stream().mapToLong(Long::longValue).average().orElse(0.0);
    }

    public String getMostBorrowedGenre() {
        if (records.isEmpty()) return "No books borrowed";

        Map<String, Long> genreCounts = records.stream()
                .map(r -> r.getBook().getGenre())
                .collect(Collectors.groupingBy(g -> g, Collectors.counting()));

        return genreCounts.entrySet().stream()
                .sorted((e1, e2) -> {
                    int cmp = Long.compare(e2.getValue(), e1.getValue()); // sort by count desc
                    if (cmp == 0) return e1.getKey().compareTo(e2.getKey()); // tie → alphabetical
                    return cmp;
                })
                .findFirst()
                .map(e -> e.getKey() + " (" + e.getValue() + ")")
                .orElse("No books borrowed");
    }
}

public class BookSolution {
    public static void main(String[] args) {
        Book b1 = new Book("111", "Java Basics", "Author A", "Programming");
        Book b2 = new Book("222", "History of India", "Author B", "History");
        Book b3 = new Book("333", "Advanced Java", "Author A", "Programming");

        MemberAccount member = new MemberAccount();

        member.addBorrowRecord(new BorrowRecord(b1, LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 10)));
        member.addBorrowRecord(new BorrowRecord(b2, LocalDate.of(2026, 2, 1), LocalDate.of(2026, 2, 5)));
        member.addBorrowRecord(new BorrowRecord(b3, LocalDate.of(2026, 3, 1), null)); // not returned yet

        System.out.println("Total borrowed: " + member.getTotalBorrowedBooks());
        System.out.println("Currently borrowed: " + member.getCurrentBorrowedCount());
        System.out.println("Returned count: " + member.getReturnedCount());
        System.out.println("Average holding days: " + member.getAverageHoldingDays());
        System.out.println("Most borrowed genre: " + member.getMostBorrowedGenre());
    }
}
