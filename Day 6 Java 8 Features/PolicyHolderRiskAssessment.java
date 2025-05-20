import java.util.*;
import java.util.stream.Collectors;

class PolicyHolder {
    private int holderId;
    private String name;
    private int age;
    private String policyType;
    private double premiumAmount;

    public PolicyHolder(int holderId, String name, int age, String policyType, double premiumAmount) {
        this.holderId = holderId;
        this.name = name;
        this.age = age;
        this.policyType = policyType;
        this.premiumAmount = premiumAmount;
    }

    public int getHolderId() { return holderId; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getPolicyType() { return policyType; }
    public double getPremiumAmount() { return premiumAmount; }
}

class RiskAssessment {
    private int holderId;
    private String name;
    private double riskScore;

    public RiskAssessment(int holderId, String name, double riskScore) {
        this.holderId = holderId;
        this.name = name;
        this.riskScore = riskScore;
    }

    public int getHolderId() { return holderId; }
    public String getName() { return name; }
    public double getRiskScore() { return riskScore; }

    @Override
    public String toString() {
        return "RiskAssessment{" +
               "holderId=" + holderId +
               ", name='" + name + '\'' +
               ", riskScore=" + String.format("%.3f", riskScore) +
               '}';
    }
}

public class PolicyHolderRiskAssessment {

    public static void assessRisk(List<PolicyHolder> holders) {
        // 1. Filter policyholders with policyType "Life" and age > 60
        List<PolicyHolder> filtered = holders.stream()
            .filter(h -> "Life".equalsIgnoreCase(h.getPolicyType()) && h.getAge() > 60)
            .collect(Collectors.toList());

        // 2. Transform to RiskAssessment with riskScore = premiumAmount / age
        List<RiskAssessment> riskAssessments = filtered.stream()
            .map(h -> new RiskAssessment(h.getHolderId(), h.getName(), h.getPremiumAmount() / h.getAge()))
            .collect(Collectors.toList());

        // 3. Sort risk assessments by riskScore descending
        riskAssessments.sort(Comparator.comparingDouble(RiskAssessment::getRiskScore).reversed());

        // 4. Categorize into "High Risk" and "Low Risk"
        Map<String, List<RiskAssessment>> categorized = riskAssessments.stream()
            .collect(Collectors.groupingBy(r -> r.getRiskScore() > 0.5 ? "High Risk" : "Low Risk"));

        // Output the categorized risk assessments
        System.out.println("Risk Assessments Categorized:");
        categorized.forEach((category, list) -> {
            System.out.println("\n" + category + ":");
            list.forEach(System.out::println);
        });
    }

    public static void main(String[] args) {
        List<PolicyHolder> holders = List.of(
            new PolicyHolder(1, "John Doe", 65, "Life", 40_000),
            new PolicyHolder(2, "Jane Smith", 70, "Auto", 15_000),
            new PolicyHolder(3, "Bob Johnson", 75, "Life", 25_000),
            new PolicyHolder(4, "Alice Williams", 62, "Life", 20_000),
            new PolicyHolder(5, "Chris Davis", 59, "Life", 30_000),
            new PolicyHolder(6, "Nancy Wilson", 68, "Life", 28_000)
        );

        assessRisk(holders);
    }
}




