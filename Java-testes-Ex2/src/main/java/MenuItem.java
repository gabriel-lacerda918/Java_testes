// MenuItem.java
public class MenuItem {
    private String name;
    private double price;
    private String description;
    private String category;  // Added as an improvement

    public MenuItem(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = "General";  // Default category
    }

    public MenuItem(String name, double price, String description, String category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return name + " - R$" + String.format("%.2f", price) + " - " + description + " (" + category + ")";
    }
}