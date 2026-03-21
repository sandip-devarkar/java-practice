package com.interview;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

class Account {
    private String accountNumber;
    private String accountHolder;
    private String accountType;

    public Account(String accountNumber, String accountHolder, String accountType) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.accountType = accountType;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolder() { return accountHolder; }
    public String getAccountType() { return accountType; }
}
class Transaction {
    private Account account;
    private String type;   // "Deposit" or "Withdrawal"
    private double amount;
    private String date;   // e.g. "2026-03-21"
    private String time;   // e.g. "14:30"

    public Transaction(Account account, String type, double amount, String date, String time) {
        this.account = account;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.time = time;
    }

    public Account getAccount() { return account; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public String getDate() { return date; }
    public String getTime() { return time; }
}

class TransactionMonitor {
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public int getTotalTransactions() {
        return transactions.size();
    }

    public double getTotalDeposits() {
        return transactions.stream()
                .filter(t -> t.getType().equalsIgnoreCase("Deposit"))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getTotalWithdrawals() {
        return transactions.stream()
                .filter(t -> t.getType().equalsIgnoreCase("Withdrawal"))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getAverageTransactionAmount() {
        if (transactions.isEmpty()) return 0.0;
        return transactions.stream().mapToDouble(Transaction::getAmount).average().orElse(0.0);
    }

    public long getTransactionCountForAccount(String accountNumber) {
        return transactions.stream()
                .filter(t -> t.getAccount().getAccountNumber().equals(accountNumber))
                .count();
    }

    // Detect suspicious pattern: multiple large withdrawals in short time
    public boolean detectLargeWithdrawalPattern(String accountNumber, double thresholdAmount, int maxMinutes, int minCount) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        List<LocalDateTime> withdrawalTimes = transactions.stream()
                .filter(t -> t.getAccount().getAccountNumber().equals(accountNumber))
                .filter(t -> t.getType().equalsIgnoreCase("Withdrawal") && t.getAmount() >= thresholdAmount)
                .map(t -> LocalDateTime.parse(t.getDate() + " " + t.getTime(), formatter))
                .sorted()
                .collect(Collectors.toList());

        // Check if there are at least minCount withdrawals within maxMinutes
        for (int i = 0; i < withdrawalTimes.size(); i++) {
            int count = 1;
            for (int j = i + 1; j < withdrawalTimes.size(); j++) {
                long diff = java.time.Duration.between(withdrawalTimes.get(i), withdrawalTimes.get(j)).toMinutes();
                if (diff <= maxMinutes) {
                    count++;
                    if (count >= minCount) return true;
                } else break;
            }
        }
        return false;
    }
}

public class TransactionMonitoringSystem {

	public static void main(String[] args) {
        Account acc1 = new Account("A001", "Sandip", "Savings");

        TransactionMonitor monitor = new TransactionMonitor();

        monitor.addTransaction(new Transaction(acc1, "Deposit", 5000, "2026-03-20", "09:00"));
        monitor.addTransaction(new Transaction(acc1, "Withdrawal", 20000, "2026-03-21", "10:00"));
        monitor.addTransaction(new Transaction(acc1, "Withdrawal", 25000, "2026-03-21", "10:20"));
        monitor.addTransaction(new Transaction(acc1, "Withdrawal", 30000, "2026-03-21", "10:45"));

        System.out.println("Total Transactions: " + monitor.getTotalTransactions());
        System.out.println("Total Deposits: " + monitor.getTotalDeposits());
        System.out.println("Total Withdrawals: " + monitor.getTotalWithdrawals());
        System.out.println("Average Transaction Amount: " + monitor.getAverageTransactionAmount());
        System.out.println("Transactions for A001: " + monitor.getTransactionCountForAccount("A001"));

        boolean suspicious = monitor.detectLargeWithdrawalPattern("A001", 20000, 60, 3);
        System.out.println("Suspicious Pattern Detected: " + suspicious);
    }

}
