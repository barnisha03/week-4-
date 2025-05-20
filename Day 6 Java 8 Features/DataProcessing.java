import java.util.*;
import java.util.stream.Collectors;

// Employee class
class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return id + ": " + name + ", Dept: " + department + ", Salary: " + salary;
    }
}

// Sale class
class Sale {
    private int productId;
    private int quantity;
    private double price;

    public Sale(int productId, int quantity, double price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
}

// ProductSales class
class ProductSales {
    private int productId;
    private double totalRevenue;

    public ProductSales(int productId, double totalRevenue) {
        this.productId = productId;
        this.totalRevenue = totalRevenue;
    }

    public int getProductId() { return productId; }
    public double getTotalRevenue() { return totalRevenue; }

    @Override
    public String toString() {
        return "ProductId: " + productId + ", Total Revenue: " + totalRevenue;
    }
}

public class DataProcessing {

    // Problem 1: Employee Data Processing
    public static void processEmployees(List<Employee> employees) {
        System.out.println("=== Employee Data Processing ===");

        // 1. Filter employees from Engineering with salary > 80000
        List<Employee> filteredSorted = employees.stream()
            .filter(e -> e.getDepartment().equals("Engineering") && e.getSalary() > 80000)
            // 2. Sort descending by salary
            .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
            .collect(Collectors.toList());

        // Print filtered and sorted employees
        System.out.println("Filtered and Sorted Employees:");
        filteredSorted.forEach(System.out::println);

        // 3. Group by department
        Map<String, List<Employee>> groupedByDept = filteredSorted.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment));

        System.out.println("\nGrouped by Department:");
        groupedByDept.forEach((dept, empList) -> {
            System.out.println(dept + ":");
            empList.forEach(e -> System.out.println("  " + e));
        });

        // 4. Calculate average salary by department
        Map<String, Double> avgSalaryByDept = filteredSorted.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment,
                    Collectors.averagingDouble(Employee::getSalary)));

        System.out.println("\nAverage Salary by Department:");
        avgSalaryByDept.forEach((dept, avgSalary) ->
            System.out.println(dept + ": " + avgSalary)
        );

        System.out.println();
    }

    // Problem 2: Product Sales Analysis
    public static List<ProductSales> analyzeSales(List<Sale> sales, int topN) {
        System.out.println("=== Product Sales Analysis ===");

        // Filter sales with quantity > 10 and calculate total revenue grouped by productId
        Map<Integer, Double> revenueByProduct = sales.stream()
            .filter(sale -> sale.getQuantity() > 10)
            .collect(Collectors.groupingBy(
                Sale::getProductId,
                Collectors.summingDouble(sale -> sale.getQuantity() * sale.getPrice())
            ));

        // Transform to ProductSales, sort by revenue desc
        List<ProductSales> productSalesList = revenueByProduct.entrySet().stream()
            .map(e -> new ProductSales(e.getKey(), e.getValue()))
            .sorted(Comparator.comparingDouble(ProductSales::getTotalRevenue).reversed())
            .collect(Collectors.toList());

        // Limit to top N
        List<ProductSales> topProducts = productSalesList.stream()
            .limit(topN)
            .collect(Collectors.toList());

        System.out.println("Top " + topN + " products by total revenue:");
        topProducts.forEach(System.out::println);

        System.out.println();
        return topProducts;
    }

    public static void main(String[] args) {
        // Sample employees
        List<Employee> employees = Arrays.asList(
            new Employee(1, "Alice", "Engineering", 90000),
            new Employee(2, "Bob", "Engineering", 85000),
            new Employee(3, "Charlie", "HR", 70000),
            new Employee(4, "David", "Engineering", 75000),
            new Employee(5, "Eve", "Marketing", 95000),
            new Employee(6, "Frank", "Engineering", 120000)
        );

        processEmployees(employees);

        // Sample sales
        List<Sale> sales = Arrays.asList(
            new Sale(101, 15, 10.0),
            new Sale(102, 5, 20.0),
            new Sale(103, 25, 7.5),
            new Sale(101, 10, 10.0),
            new Sale(104, 30, 5.0),
            new Sale(105, 12, 12.0),
            new Sale(106, 9, 15.0),
            new Sale(103, 10, 7.5),
            new Sale(107, 20, 3.0)
        );

        analyzeSales(sales, 5);
    }
}
