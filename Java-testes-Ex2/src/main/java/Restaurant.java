// Restaurant.java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Restaurant {
    private String name;
    private List<MenuItem> menu;
    private Map<String, Order> orders;
    private int nextOrderNumber;

    public Restaurant(String name) {
        this.name = name;
        this.menu = new ArrayList<>();
        this.orders = new HashMap<>();
        this.nextOrderNumber = 1001; // Start with order number 1001
    }

    // Menu management methods
    public void addMenuItem(MenuItem item) {
        menu.add(item);
    }

    public boolean removeMenuItem(String itemName) {
        return menu.removeIf(item -> item.getName().equals(itemName));
    }

    public MenuItem findMenuItem(String itemName) {
        for (MenuItem item : menu) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    // Order management methods
    public Order createOrder(String customerName) {
        String orderNumber = generateOrderNumber();
        Order order = new Order(orderNumber, customerName);
        orders.put(orderNumber, order);
        return order;
    }

    private String generateOrderNumber() {
        return "ORD-" + nextOrderNumber++;
    }

    public Order findOrder(String orderNumber) {
        return orders.get(orderNumber);
    }

    public boolean cancelOrder(String orderNumber) {
        Order order = orders.get(orderNumber);
        if (order != null) {
            order.setStatus("Cancelled");
            return true;
        }
        return false;
    }

    public boolean completeOrder(String orderNumber) {
        Order order = orders.get(orderNumber);
        if (order != null) {
            order.setStatus("Completed");
            return true;
        }
        return false;
    }

    // Menu query methods (improvements)
    public List<MenuItem> getMenuItemsByCategory(String category) {
        return menu.stream()
                .filter(item -> item.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    public List<MenuItem> getMenuItemsBelowPrice(double maxPrice) {
        return menu.stream()
                .filter(item -> item.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    // Order query methods (improvements)
    public List<Order> getPendingOrders() {
        return orders.values().stream()
                .filter(order -> order.getStatus().equals("Pending"))
                .collect(Collectors.toList());
    }

    public double getTotalSales() {
        return orders.values().stream()
                .filter(order -> order.getStatus().equals("Completed"))
                .mapToDouble(Order::getTotalPrice)
                .sum();
    }

    // Getters
    public String getName() {
        return name;
    }

    public List<MenuItem> getMenu() {
        return new ArrayList<>(menu);
    }

    public Map<String, Order> getOrders() {
        return new HashMap<>(orders);
    }
}