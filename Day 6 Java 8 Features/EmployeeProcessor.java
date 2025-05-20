import java.util.*;
import java.util.stream.*;

public class EmployeeProcessor {

    public static void processEmployees(List<Employee> employees) {
        List<Employee> filteredSorted = employees.stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase("Engineering") && e.getSalary() > 80000)
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .collect(Collectors.toList());

        System.out.println("Filtered & Sorted Employees:");
        filteredSorted.forEach(System.out::println);

        Map<String, List<Employee>> groupedByDept = filteredSorted.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        System.out.println("\nGrouped by Department:");
        groupedByDept.forEach((dept, empList) -> {
            System.out.println(dept + ": " + empList);
        });

        Map<String, Double> averageSalaryByDept = filteredSorted.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));

        System.out.println("\nAverage Salary by Department:");
        averageSalaryByDept.forEach((dept, avgSalary) -> {
            System.out.printf("%s: %.2f%n", dept, avgSalary);
        });
    }

    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee(1, "Alice", "Engineering", 90000),
                new Employee(2, "Bob", "Engineering", 85000),
                new Employee(3, "Charlie", "HR", 70000),
                new Employee(4, "David", "Engineering", 75000),
                new Employee(5, "Eve", "Marketing", 95000),
                new Employee(6, "Frank", "Engineering", 120000)
        );

        processEmployees(employees);
    }
}

// Employee class in the same file
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
        return String.format("%s (%.2f)", name, salary);
    }
}
















