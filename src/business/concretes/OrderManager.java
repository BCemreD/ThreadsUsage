package src.business.concretes;

import java.util.*;

import java.util.Scanner;

import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;

import java.util.concurrent.TimeUnit;

import src.business.abstracts.IOrderService;

import src.dataAccess.CustomerDatabaseExample;

import src.dataAccess.DatabaseExample;

import src.entity.concretes.Customer;

import src.entity.concretes.Order;

import src.entity.concretes.Product;

public class OrderManager implements IOrderService {

    private ExecutorService executorService = Executors.newFixedThreadPool(5);
    private List<Order> orders = new ArrayList<>();
    private List<Customer> customers = Collections.synchronizedList(new ArrayList<>());
    CustomerManager customerManager = new CustomerManager();
    private Scanner s = new Scanner(System.in);

    @Override
    public Order addOrder(Customer customer, List<Product> products) {
    	synchronized (this) {
        System.out.println("Enter customer name: ");
        String customerName = s.nextLine();
        System.out.println("Enter customer surname: ");
        String customerSurname = s.nextLine();

        // **existing customers**
        List<Customer> allCustomers = new ArrayList<>(CustomerDatabaseExample.getAllCustomers());

        // **find customer**
        Customer c = allCustomers.stream()
                .filter(existingCustomer -> existingCustomer.getName().equalsIgnoreCase(customerName)
                        && existingCustomer.getSurname().equalsIgnoreCase(customerSurname))
                .findFirst().orElse(null);

        if (c == null) {
            System.out.println("Customer not found. Please check your name and surname or register first.");
            return null;
        }

        // **existing products**
        List<Product> allProducts = new ArrayList<>(DatabaseExample.getAllProducts());

        int totalQuantity = 0;
        List<Product> orderProducts = new ArrayList<>();

        while (totalQuantity < 5) {
            System.out.println("Enter the product name: ");
            String productName = s.nextLine();

            Product productToAdd = allProducts.stream()
                    .filter(p -> p.getProductName().equalsIgnoreCase(productName))
                    .findFirst().orElse(null);

            if (productToAdd == null) {
                System.out.println("Product not found. Try again.");
                continue;
            }

            System.out.println("Enter the quantity of " + productToAdd.getProductName() + " you want to order (max: "
                    + (5 - totalQuantity) + "): ");
            int quantity = s.nextInt();
            s.nextLine();

            if (quantity <= 0 || quantity > (5 - totalQuantity) || quantity > productToAdd.getStockQuantity()) {
                System.out.println("Invalid quantity. Try again.");
                continue;
            }

            for (int i = 0; i < quantity; i++) {
                orderProducts.add(productToAdd);
            }

            totalQuantity += quantity;

            if (totalQuantity < 5) {
                System.out.println(
                        "You have added " + totalQuantity + " products. Type 'OK' to finish or press Enter to continue.");
                String response = s.nextLine().trim().toUpperCase();
                if (response.equals("OK")) {
                    break;
                }
            }
        }

        Order newOrder = new Order(orderProducts, c, "Pending");
        orders.add(newOrder);
        c.getOrderHistory().add(newOrder);

        System.out.println("Order has been created with status: Pending");

        updateStockQuantities(newOrder);

        return newOrder;
    	}
    }

    private void updateStockQuantities(Order order) {
    	ProductManager productManager = new ProductManager();
    	for (Product orderedProduct : new HashSet<>(order.getProducts())) {
    		//used hashset bec. hashset doesn't consist the same object several times. o1 time c.
            int orderQuantity = Collections.frequency(order.getProducts(), orderedProduct);
            productManager.updateStockQuantity(orderedProduct, orderQuantity);
        }
    }

    @Override
    public Order cancelOrder(int orderId) {
        Order orderToCancel = orders.stream().filter(order -> order.getOrderId() == orderId).findFirst().orElse(null);

        if (orderToCancel == null) {
            System.out.println("Order not found.");
            return null;
        }

     // Refresh the stock quantity
        ProductManager productManager = new ProductManager();
        for (Product p : new HashSet<>(orderToCancel.getProducts())) {
            int orderQuantity = Collections.frequency(orderToCancel.getProducts(), p);
            productManager.updateStockQuantity(p, -orderQuantity);
        }

        // Remove the order from the list
        orders.remove(orderToCancel);
        
     // Delete from customer's order history
        Customer customer = orderToCancel.getCustomer();
        customer.getOrderHistory().remove(orderToCancel);

        System.out.println("Order #" + orderId + " has been cancelled. The stock has been restored.");

        return orderToCancel;
    }

    @Override
    public String orderProcess() {
        if (orders.isEmpty()) {
            return "No orders to process.";
        }

        for (Order order : orders) {
            if (!order.getStatus().equals("Completed") && !order.getStatus().equals("Cancelled")) {
                order.setStatus("Processing");
                executorService.submit(new OrderProcessor(order));
            }
        }

        return "Orders are being processed asynchronously...";
    }

    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}
