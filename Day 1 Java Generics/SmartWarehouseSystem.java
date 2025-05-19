import java.util.*;

// Abstract class
abstract class WarehouseItem {
    private String name;

    public WarehouseItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String getDetails();

    @Override
    public String toString() {
        return getName() + " - " + getDetails();
    }
}

// Subclass: Electronics
class Electronics extends WarehouseItem {
    private int warrantyYears;

    public Electronics(String name, int warrantyYears) {
        super(name);
        this.warrantyYears = warrantyYears;
    }

    @Override
    public String getDetails() {
        return "Electronics with " + warrantyYears + " year(s) warranty";
    }
}

// Subclass: Groceries
class Groceries extends WarehouseItem {
    private String expiryDate;

    public Groceries(String name, String expiryDate) {
        super(name);
        this.expiryDate = expiryDate;
    }

    @Override
    public String getDetails() {
        return "Groceries, expires on " + expiryDate;
    }
}

// Subclass: Furniture
class Furniture extends WarehouseItem {
    private String material;

    public Furniture(String name, String material) {
        super(name);
        this.material = material;
    }

    @Override
    public String getDetails() {
        return "Furniture made of " + material;
    }
}

// Generic Storage Class
class Storage<T extends WarehouseItem> {
    private List<T> items;

    public Storage() {
        items = new ArrayList<>();
    }

    public void addItem(T item) {
        items.add(item);
    }

    public List<T> getItems() {
        return items;
    }
}

// Utility Class for Display
class WarehouseUtility {
    public static void displayItems(List<? extends WarehouseItem> items) {
        for (WarehouseItem item : items) {
            System.out.println(item);
        }
    }
}

// Main Class
public class SmartWarehouseSystem {
    public static void main(String[] args) {
        Storage<Electronics> electronicsStorage = new Storage<>();
        electronicsStorage.addItem(new Electronics("Laptop", 2));
        electronicsStorage.addItem(new Electronics("Smartphone", 1));

        Storage<Groceries> groceriesStorage = new Storage<>();
        groceriesStorage.addItem(new Groceries("Milk", "2025-06-01"));
        groceriesStorage.addItem(new Groceries("Bread", "2025-05-21"));

        Storage<Furniture> furnitureStorage = new Storage<>();
        furnitureStorage.addItem(new Furniture("Chair", "Wood"));
        furnitureStorage.addItem(new Furniture("Table", "Metal"));

        System.out.println("=== Electronics ===");
        WarehouseUtility.displayItems(electronicsStorage.getItems());

        System.out.println("\n=== Groceries ===");
        WarehouseUtility.displayItems(groceriesStorage.getItems());

        System.out.println("\n=== Furniture ===");
        WarehouseUtility.displayItems(furnitureStorage.getItems());
    }
}
