// Main.java
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Sistema de Pedidos de Restaurante");

        // Create a new restaurant
        Restaurant restaurant = new Restaurant("Delícias Gourmet");

        // Add menu items
        restaurant.addMenuItem(new MenuItem("Hambúrguer", 9.99, "Hambúrguer clássico de carne com queijo", "Principal"));
        restaurant.addMenuItem(new MenuItem("X-Burguer", 10.99, "Hambúrguer com queijo extra", "Principal"));
        restaurant.addMenuItem(new MenuItem("Hambúrguer Vegetariano", 8.99, "Hambúrguer à base de plantas", "Principal"));
        restaurant.addMenuItem(new MenuItem("Batata Frita", 3.99, "Batatas fritas crocantes", "Acompanhamento"));
        restaurant.addMenuItem(new MenuItem("Anéis de Cebola", 4.99, "Anéis de cebola empanados", "Acompanhamento"));
        restaurant.addMenuItem(new MenuItem("Salada", 7.99, "Salada fresca de jardim", "Acompanhamento"));
        restaurant.addMenuItem(new MenuItem("Refrigerante", 1.99, "Refrigerante de sua escolha", "Bebida"));
        restaurant.addMenuItem(new MenuItem("Milkshake", 4.99, "Milkshake cremoso de baunilha", "Bebida"));
        restaurant.addMenuItem(new MenuItem("Sorvete", 3.99, "Sorvete de baunilha", "Sobremesa"));

        // Display the menu
        System.out.println("\nCardápio:");
        for (MenuItem item : restaurant.getMenu()) {
            System.out.println(item);
        }

        // Filter menu items
        System.out.println("\nPratos principais:");
        for (MenuItem item : restaurant.getMenuItemsByCategory("Principal")) {
            System.out.println(item);
        }

        System.out.println("\nItens abaixo de R$5:");
        for (MenuItem item : restaurant.getMenuItemsBelowPrice(5.0)) {
            System.out.println(item);
        }

        // Create orders
        System.out.println("\nCriando pedidos...");

        // Order 1
        Order order1 = restaurant.createOrder("João Silva");
        order1.addMenuItem(restaurant.findMenuItem("Hambúrguer"));
        order1.addMenuItem(restaurant.findMenuItem("Batata Frita"));
        order1.addMenuItem(restaurant.findMenuItem("Refrigerante"));

        System.out.println("\nDetalhes do Pedido 1:");
        System.out.println(order1.viewOrderDetails());

        // Order 2
        Order order2 = restaurant.createOrder("Maria Oliveira");
        order2.addMenuItem(restaurant.findMenuItem("Hambúrguer Vegetariano"));
        order2.addMenuItem(restaurant.findMenuItem("Salada"));
        order2.addMenuItem(restaurant.findMenuItem("Milkshake"));

        System.out.println("\nDetalhes do Pedido 2:");
        System.out.println(order2.viewOrderDetails());

        // Process orders
        System.out.println("\nProcessando pedidos...");
        restaurant.completeOrder(order1.getOrderNumber());

        System.out.println("\nPedidos pendentes: " + restaurant.getPendingOrders().size());
        System.out.println("Total de vendas: R$" + String.format("%.2f", restaurant.getTotalSales()));

        // Run tests
        System.out.println("\nExecutando testes...");
        RestaurantTest test = new RestaurantTest();
        test.runAllTests();
    }
}