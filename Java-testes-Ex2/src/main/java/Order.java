// Order.java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {
    private String orderNumber;
    private String customerName;
    private List<MenuItem> listOfMenuItems;
    private Map<MenuItem, Integer> itemQuantities; // Improvement: track quantity of each item
    private double totalPrice;
    private LocalDateTime orderTime; // Improvement: track when order was placed
    private String status; // Improvement: track order status

    public Order(String orderNumber, String customerName) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.listOfMenuItems = new ArrayList<>();
        this.itemQuantities = new HashMap<>();
        this.totalPrice = 0.0;
        this.orderTime = LocalDateTime.now();
        this.status = "Pending";
    }

    // Methods to manage menu items in the order
    public void addMenuItem(MenuItem item) {
        listOfMenuItems.add(item);

        // Update item quantities
        itemQuantities.put(item, itemQuantities.getOrDefault(item, 0) + 1);

        // Update total price
        totalPrice += item.getPrice();
    }

    public void addMenuItem(MenuItem item, int quantity) {
        for (int i = 0; i < quantity; i++) {
            listOfMenuItems.add(item);
        }

        // Update item quantities
        itemQuantities.put(item, itemQuantities.getOrDefault(item, 0) + quantity);

        // Update total price
        totalPrice += item.getPrice() * quantity;
    }

    public boolean removeMenuItem(MenuItem item) {
        boolean removed = listOfMenuItems.remove(item);

        if (removed) {
            // Update item quantities
            int currentQuantity = itemQuantities.getOrDefault(item, 0);
            if (currentQuantity > 1) {
                itemQuantities.put(item, currentQuantity - 1);
            } else {
                itemQuantities.remove(item);
            }

            // Update total price
            totalPrice -= item.getPrice();
        }

        return removed;
    }

    public void removeAllOfItem(MenuItem item) {
        int count = 0;
        while (listOfMenuItems.remove(item)) {
            count++;
        }

        if (count > 0) {
            // Update item quantities
            itemQuantities.remove(item);

            // Update total price
            totalPrice -= item.getPrice() * count;
        }
    }

    // Calculate order total
    public double calculateTotal() {
        double total = 0.0;
        for (MenuItem item : listOfMenuItems) {
            total += item.getPrice();
        }
        totalPrice = total;
        return total;
    }

    // View order details
    public String viewOrderDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Pedido #").append(orderNumber).append("\n");
        details.append("Cliente: ").append(customerName).append("\n");
        details.append("Pedido feito em: ").append(orderTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\n");
        details.append("Status: ").append(translateStatus(status)).append("\n\n");
        details.append("Itens:\n");

        for (Map.Entry<MenuItem, Integer> entry : itemQuantities.entrySet()) {
            MenuItem item = entry.getKey();
            int quantity = entry.getValue();
            details.append(quantity).append("x ").append(item.getName())
                    .append(" (R$").append(String.format("%.2f", item.getPrice())).append(" cada)")
                    .append(" - R$").append(String.format("%.2f", item.getPrice() * quantity))
                    .append("\n");
        }

        details.append("\nTotal: R$").append(String.format("%.2f", totalPrice));
        return details.toString();
    }

    // Helper method to translate status
    private String translateStatus(String status) {
        switch (status) {
            case "Pending": return "Pendente";
            case "Completed": return "Concluído";
            case "Cancelled": return "Cancelado";
            default: return status;
        }
    }

    // Getters and setters
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<MenuItem> getListOfMenuItems() {
        return new ArrayList<>(listOfMenuItems);
    }

    public void setListOfMenuItems(List<MenuItem> listOfMenuItems) {
        this.listOfMenuItems = new ArrayList<>(listOfMenuItems);
        recalculateQuantitiesAndTotal();
    }

    private void recalculateQuantitiesAndTotal() {
        itemQuantities.clear();
        totalPrice = 0.0;

        for (MenuItem item : listOfMenuItems) {
            itemQuantities.put(item, itemQuantities.getOrDefault(item, 0) + 1);
            totalPrice += item.getPrice();
        }
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<MenuItem, Integer> getItemQuantities() {
        return new HashMap<>(itemQuantities);
    }
}