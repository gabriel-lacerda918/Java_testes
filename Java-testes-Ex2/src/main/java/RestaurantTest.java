// RestaurantTest.java
import java.util.List;

public class RestaurantTest {
    private Restaurant restaurant;
    private MenuItem item1, item2, item3;

    // Setup method to initialize test data
    private void setup() {
        restaurant = new Restaurant("Restaurante de Teste");

        // Create test menu items
        item1 = new MenuItem("Hambúrguer", 9.99, "Hambúrguer clássico de carne com queijo", "Principal");
        item2 = new MenuItem("Batata Frita", 3.99, "Batatas fritas crocantes", "Acompanhamento");
        item3 = new MenuItem("Refrigerante", 1.99, "Refrigerante de sua escolha", "Bebida");

        // Add menu items to the restaurant
        restaurant.addMenuItem(item1);
        restaurant.addMenuItem(item2);
        restaurant.addMenuItem(item3);
    }

    // Test adding menu items
    private void testAddMenuItem() {
        setup();
        int initialSize = restaurant.getMenu().size();
        MenuItem newItem = new MenuItem("Salada", 7.99, "Salada fresca de jardim", "Acompanhamento");
        restaurant.addMenuItem(newItem);

        assert restaurant.getMenu().size() == initialSize + 1 : "Item do cardápio não foi adicionado corretamente";
        assert restaurant.findMenuItem("Salada") != null : "Item do cardápio não pode ser encontrado pelo nome";

        System.out.println("Teste de adicionar item ao cardápio aprovado!");
    }

    // Test removing menu items
    private void testRemoveMenuItem() {
        setup();
        int initialSize = restaurant.getMenu().size();
        boolean result = restaurant.removeMenuItem("Hambúrguer");

        assert result : "A remoção do item do cardápio deveria retornar verdadeiro";
        assert restaurant.getMenu().size() == initialSize - 1 : "Item do cardápio não foi removido corretamente";
        assert restaurant.findMenuItem("Hambúrguer") == null : "Item do cardápio não deveria ser encontrado após remoção";

        System.out.println("Teste de remover item do cardápio aprovado!");
    }

    // Test creating an order
    private void testCreateOrder() {
        setup();
        Order order = restaurant.createOrder("João Silva");

        assert order != null : "Pedido não deveria ser nulo";
        assert order.getCustomerName().equals("João Silva") : "Nome do cliente não foi definido corretamente";
        assert order.getStatus().equals("Pending") : "Novo pedido deveria ter status 'Pending'";
        assert restaurant.findOrder(order.getOrderNumber()) != null : "Pedido deveria ser armazenado nos pedidos do restaurante";

        System.out.println("Teste de criar pedido aprovado!");
    }

    // Test adding items to an order
    private void testAddItemToOrder() {
        setup();
        Order order = restaurant.createOrder("João Silva");
        double initialTotal = order.getTotalPrice();

        order.addMenuItem(item1);  // Add a burger

        assert order.getListOfMenuItems().size() == 1 : "Pedido deveria ter 1 item";
        assert Math.abs(order.getTotalPrice() - (initialTotal + item1.getPrice())) < 0.001 : "Total do pedido não foi atualizado corretamente";

        // Add multiple of the same item
        order.addMenuItem(item2, 2);  // Add 2 fries

        assert order.getListOfMenuItems().size() == 3 : "Pedido deveria ter 3 itens no total";
        assert order.getItemQuantities().get(item2) == 2 : "Deveria ter 2 batatas fritas no pedido";

        System.out.println("Teste de adicionar item ao pedido aprovado!");
    }

    // Test removing items from an order
    private void testRemoveItemFromOrder() {
        setup();
        Order order = restaurant.createOrder("João Silva");

        // Add items
        order.addMenuItem(item1);  // Add a burger
        order.addMenuItem(item2);  // Add fries
        order.addMenuItem(item2);  // Add another fries

        double priceBeforeRemoval = order.getTotalPrice();
        boolean result = order.removeMenuItem(item2);  // Remove one fries

        assert result : "A remoção do item deveria retornar verdadeiro";
        assert order.getListOfMenuItems().size() == 2 : "Pedido deveria ter 2 itens após remoção";
        assert Math.abs(order.getTotalPrice() - (priceBeforeRemoval - item2.getPrice())) < 0.001 : "Total do pedido não foi atualizado corretamente após remoção";
        assert order.getItemQuantities().get(item2) == 1 : "Deveria ter 1 batata frita restante";

        System.out.println("Teste de remover item do pedido aprovado!");
    }

    // Test order details view
    private void testViewOrderDetails() {
        setup();
        Order order = restaurant.createOrder("João Silva");

        // Add items
        order.addMenuItem(item1);  // Add a burger
        order.addMenuItem(item2, 2);  // Add 2 fries

        String details = order.viewOrderDetails();

        assert details.contains("João Silva") : "Detalhes do pedido deveriam incluir o nome do cliente";
        assert details.contains(item1.getName()) : "Detalhes do pedido deveriam incluir o nome do hambúrguer";
        assert details.contains("2x " + item2.getName()) : "Detalhes do pedido deveriam mostrar 2 batatas fritas";
        assert details.contains(String.format("%.2f", order.getTotalPrice())) : "Detalhes do pedido deveriam incluir o preço total";

        System.out.println("Teste de visualizar detalhes do pedido aprovado!");
    }

    // Test order status changes
    private void testOrderStatusChanges() {
        setup();
        Order order = restaurant.createOrder("João Silva");
        String orderNumber = order.getOrderNumber();

        boolean result = restaurant.completeOrder(orderNumber);

        assert result : "Concluir pedido deveria retornar verdadeiro";
        assert restaurant.findOrder(orderNumber).getStatus().equals("Completed") : "Status do pedido deveria ser 'Completed'";

        order = restaurant.createOrder("Maria Oliveira");
        String orderNumber2 = order.getOrderNumber();

        boolean cancelResult = restaurant.cancelOrder(orderNumber2);

        assert cancelResult : "Cancelar pedido deveria retornar verdadeiro";
        assert restaurant.findOrder(orderNumber2).getStatus().equals("Cancelled") : "Status do pedido deveria ser 'Cancelled'";

        System.out.println("Teste de alterações de status do pedido aprovado!");
    }

    // Test menu filtering
    private void testMenuFiltering() {
        setup();

        // Add more items
        restaurant.addMenuItem(new MenuItem("Bife", 19.99, "Bife de costela grelhado", "Principal"));
        restaurant.addMenuItem(new MenuItem("Bolo", 6.99, "Bolo de chocolate", "Sobremesa"));

        List<MenuItem> mainItems = restaurant.getMenuItemsByCategory("Principal");
        List<MenuItem> cheapItems = restaurant.getMenuItemsBelowPrice(5.0);

        assert mainItems.size() == 2 : "Deveria encontrar 2 itens da categoria 'Principal'";
        assert cheapItems.size() == 2 : "Deveria encontrar 2 itens abaixo de R$5,00";

        System.out.println("Teste de filtragem do cardápio aprovado!");
    }

    // Run all tests
    public void runAllTests() {
        System.out.println("Executando testes do Sistema de Pedidos de Restaurante...");
        testAddMenuItem();
        testRemoveMenuItem();
        testCreateOrder();
        testAddItemToOrder();
        testRemoveItemFromOrder();
        testViewOrderDetails();
        testOrderStatusChanges();
        testMenuFiltering();
        System.out.println("Todos os testes foram aprovados com sucesso!");
    }

    public static void main(String[] args) {
        RestaurantTest test = new RestaurantTest();
        test.runAllTests();
    }
}