import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class Transaction {
    private int transactionId;
    private String policyNumber;
    private double amount;
    private LocalDate transactionDate;
    private boolean isFraudulent;

    public Transaction(int transactionId, String policyNumber, double amount, LocalDate transactionDate, boolean isFraudulent) {
        this.transactionId = transactionId;
        this.policyNumber = policyNumber;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.isFraudulent = isFraudulent;
    }

    public int getTransactionId() { return transactionId; }
    public String getPolicyNumber() { return policyNumber; }
    public double getAmount() { return amount; }
    public LocalDate getTransactionDate() { return transactionDate; }
    public boolean isFraudulent() { return isFraudulent; }
}

public class FraudDetection {

    public static void detectFraud(List<Transaction> transactions) {
        // 1. Filter fraudulent transactions with amount > 10,000
        List<Transaction> filtered = transactions.stream()
            .filter(t -> t.isFraudulent() && t.getAmount() > 10_000)
            .collect(Collectors.toList());

        // 2. Group by policyNumber
        Map<String, List<Transaction>> grouped = filtered.stream()
            .collect(Collectors.groupingBy(Transaction::getPolicyNumber));

        // 3. Aggregate total count and total fraud amount per policy
        Map<String, FraudStats> fraudStatsMap = new HashMap<>();
        for (Map.Entry<String, List<Transaction>> entry : grouped.entrySet()) {
            String policy = entry.getKey();
            List<Transaction> txns = entry.getValue();

            int fraudCount = txns.size();
            double totalFraudAmount = txns.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();

            fraudStatsMap.put(policy, new FraudStats(fraudCount, totalFraudAmount));
        }

        // 4. Alert for policies with >5 fraudulent transactions or fraud amount > 50,000
        System.out.println("Fraud Alerts:");
        fraudStatsMap.forEach((policy, stats) -> {
            if (stats.getCount() > 5 || stats.getTotalAmount() > 50_000) {
                System.out.printf("Policy %s - Fraudulent Transactions: %d, Total Fraud Amount: $%.2f%n",
                    policy, stats.getCount(), stats.getTotalAmount());
            }
        });
    }

    private static class FraudStats {
        private final int count;
        private final double totalAmount;

        public FraudStats(int count, double totalAmount) {
            this.count = count;
            this.totalAmount = totalAmount;
        }

        public int getCount() { return count; }
        public double getTotalAmount() { return totalAmount; }
    }

    public static void main(String[] args) {
        List<Transaction> transactions = List.of(
            new Transaction(1, "PN1001", 12000, LocalDate.of(2024, 3, 10), true),
            new Transaction(2, "PN1002", 8000, LocalDate.of(2024, 3, 11), true),
            new Transaction(3, "PN1001", 15000, LocalDate.of(2024, 3, 12), true),
            new Transaction(4, "PN1003", 7000, LocalDate.of(2024, 3, 12), false),
            new Transaction(5, "PN1001", 22000, LocalDate.of(2024, 3, 13), true),
            new Transaction(6, "PN1004", 30000, LocalDate.of(2024, 3, 14), true),
            new Transaction(7, "PN1001", 11000, LocalDate.of(2024, 3, 15), true),
            new Transaction(8, "PN1001", 13000, LocalDate.of(2024, 3, 16), true),
            new Transaction(9, "PN1004", 25000, LocalDate.of(2024, 3, 17), true),
            new Transaction(10, "PN1001", 14000, LocalDate.of(2024, 3, 18), true)
        );

        detectFraud(transactions);
    }
}





