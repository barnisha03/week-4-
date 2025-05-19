import java.util.*;

// --- Category Interfaces ---
interface Category {
    String getCategoryName();
}

enum BookCategory implements Category {
    FICTION, NON_FICTION, EDUCATIONAL;

    public String getCategoryName() {
        return name();
    }
}

enum ClothingCategory implements Category {
    MENS, WOMENS, KIDS;

    public String getCategoryName() {
        return name();
    }
}

enum GadgetCategory implements Category {
    MOBILE, LAPTOP, ACCESSORY;

    public String getCategoryName() {
        return name();
    }
}

// --- Generic Product Class ---
class Product<T extends Category> {
    private String name;
    private double price;
    private T category;

    public Product(String name, double price, T category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public T getCategory() {
        return category;
    }

    public void applyDiscount(double percentage) {
        this.price -= this.price * (percentage / 100);
    }

    @Override
    public String toString() {
        return name + " (" + category.getCategoryName() + ") - ₹" + String.format("%.2f", price);
    }
}

// --- Utility Class with Generic Method ---
class MarketplaceUtility {
    public static <T extends Product<?>> void applyDiscount(T product, double percentage) {
        System.out.println("Applying " + percentage + "% discount to " + product.getName());
        product.applyDiscount(percentage);
    }
}

// --- Product Catalog (Supports Multiple Categories) ---
class ProductCatalog {
    private List<Product<? extends Category>> catalog = new ArrayList<>();

    public void addProduct(Product<? extends Category> product) {
        catalog.add(product);
    }

    public void displayCatalog() {
        for (Product<? extends Category> product : catalog) {
            System.out.println(product);
        }
    }
}

// --- Main Class ---
public class DynamicOnlineMarketplace {
    public static void main(String[] args) {
        // Create products
        Product<BookCategory> book1 = new Product<>("Java Programming", 499.0, BookCategory.EDUCATIONAL);
        Product<ClothingCategory> shirt = new Product<>("Formal Shirt", 899.0, ClothingCategory.MENS);
        Product<GadgetCategory> phone = new Product<>("Smartphone X", 14999.0, GadgetCategory.MOBILE);

        // Apply discounts
        MarketplaceUtility.applyDiscount(book1, 10);  // 10% off
        MarketplaceUtility.applyDiscount(shirt, 20);  // 20% off

        // Add to catalog
        ProductCatalog catalog = new ProductCatalog();
        catalog.addProduct(book1);
        catalog.addProduct(shirt);
        catalog.addProduct(phone);

        // Display catalog
        System.out.println("\n=== Product Catalog ===");
        catalog.displayCatalog();
    }
}

