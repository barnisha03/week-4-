import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class Claim {
    private int claimId;
    private String policyNumber;
    private double claimAmount;
    private LocalDate claimDate;
    private String status;

    public Claim(int claimId, String policyNumber, double claimAmount, LocalDate claimDate, String status) {
        this.claimId = claimId;
        this.policyNumber = policyNumber;
        this.claimAmount = claimAmount;
        this.claimDate = claimDate;
        this.status = status;
    }

    public int getClaimId() { return claimId; }
    public String getPolicyNumber() { return policyNumber; }
    public double getClaimAmount() { return claimAmount; }
    public LocalDate getClaimDate() { return claimDate; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return "Claim{" +
               "claimId=" + claimId +
               ", policyNumber='" + policyNumber + '\'' +
               ", claimAmount=" + claimAmount +
               ", claimDate=" + claimDate +
               ", status='" + status + '\'' +
               '}';
    }
}

public class ClaimsAnalysis {

    public static void analyzeClaims(List<Claim> claims) {
        // 1. Filter claims with "Approved" status and claimAmount > 5000
        List<Claim> filteredClaims = claims.stream()
            .filter(c -> "Approved".equalsIgnoreCase(c.getStatus()) && c.getClaimAmount() > 5000)
            .collect(Collectors.toList());

        // 2. Group by policyNumber
        Map<String, List<Claim>> claimsByPolicy = filteredClaims.stream()
            .collect(Collectors.groupingBy(Claim::getPolicyNumber));

        // 3. Aggregate: Calculate total and average claimAmount per policy
        Map<String, Map<String, Double>> aggregationByPolicy = new HashMap<>();
        for (Map.Entry<String, List<Claim>> entry : claimsByPolicy.entrySet()) {
            String policy = entry.getKey();
            List<Claim> policyClaims = entry.getValue();

            double totalClaimAmount = policyClaims.stream()
                .mapToDouble(Claim::getClaimAmount).sum();
            double avgClaimAmount = policyClaims.stream()
                .mapToDouble(Claim::getClaimAmount).average().orElse(0);

            Map<String, Double> aggregates = new HashMap<>();
            aggregates.put("totalClaimAmount", totalClaimAmount);
            aggregates.put("averageClaimAmount", avgClaimAmount);

            aggregationByPolicy.put(policy, aggregates);
        }

        // 4. Top 3 policies by total claim amount
        List<Map.Entry<String, Map<String, Double>>> top3Policies = aggregationByPolicy.entrySet().stream()
            .sorted((e1, e2) -> Double.compare(e2.getValue().get("totalClaimAmount"),
                                              e1.getValue().get("totalClaimAmount")))
            .limit(3)
            .collect(Collectors.toList());

        // Display results
        System.out.println("Aggregated Claims by Policy:");
        aggregationByPolicy.forEach((policy, agg) -> {
            System.out.printf("Policy: %s, Total Claim Amount: %.2f, Average Claim Amount: %.2f%n",
                policy, agg.get("totalClaimAmount"), agg.get("averageClaimAmount"));
        });

        System.out.println("\nTop 3 Policies by Total Claim Amount:");
        for (int i = 0; i < top3Policies.size(); i++) {
            Map.Entry<String, Map<String, Double>> entry = top3Policies.get(i);
            System.out.printf("%d. Policy: %s, Total Claim Amount: %.2f%n",
                i + 1, entry.getKey(), entry.getValue().get("totalClaimAmount"));
        }
    }

    public static void main(String[] args) {
        List<Claim> claims = List.of(
            new Claim(101, "PN001", 6000, LocalDate.of(2023, 1, 10), "Approved"),
            new Claim(102, "PN002", 4000, LocalDate.of(2023, 1, 12), "Approved"),
            new Claim(103, "PN001", 12000, LocalDate.of(2023, 2, 15), "Approved"),
            new Claim(104, "PN003", 15000, LocalDate.of(2023, 2, 20), "Denied"),
            new Claim(105, "PN002", 7000, LocalDate.of(2023, 3, 5), "Approved"),
            new Claim(106, "PN004", 9000, LocalDate.of(2023, 3, 10), "Approved"),
            new Claim(107, "PN001", 5000, LocalDate.of(2023, 3, 12), "Approved"),
            new Claim(108, "PN004", 3000, LocalDate.of(2023, 3, 15), "Approved"),
            new Claim(109, "PN003", 8000, LocalDate.of(2023, 4, 1), "Approved")
        );

        analyzeClaims(claims);
    }
}



