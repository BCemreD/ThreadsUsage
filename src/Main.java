package src;

import src.business.concretes.CustomerManager;
import src.business.concretes.OrderManager;
import src.business.concretes.ProductManager;
import src.entity.concretes.Customer;
import src.entity.concretes.Order;
import src.entity.concretes.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OrderManager orderManager = new OrderManager();
        CustomerManager customerManager = new CustomerManager();
        ProductManager productManager = new ProductManager();

        System.out.println("Welcome to the Online Order System!");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1 - Add a new customer");
            System.out.println("2 - Add a new product");
            System.out.println("3 - Place an order");
            System.out.println("4 - View all products");
            System.out.println("5 - View order history");
            System.out.println("6 - Display all customers");
            System.out.println("7 - Cancel an order");
            System.out.println("8 - Exit");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Customer newCustomer = customerManager.addCustomer(null);
                    System.out.println("Customer added: " + newCustomer.getName() + " " + newCustomer.getSurname());
                    break;

                case 2:
                    Product newProduct = productManager.add(null);
                    System.out.println("Product added: " + newProduct.getProductName());
                    break;

                case 3:
                	 Customer customer = new Customer(null, null, null, null); 
                     List<Product> products = new ArrayList<>(); 
					 Order order = orderManager.addOrder(customer, products);
                    break;

                case 4:
                	productManager.displayAllProducts();
                    break;

                case 5:
                	 System.out.println("Enter customer ID to view order history: ");
                     int customerIdHistory = scanner.nextInt();
                     scanner.nextLine();
                     customerManager.orderHistory(customerIdHistory);
                    break;

                case 6:
                	customerManager.displayAllCustomers();
                    break;

                case 7:
                    System.out.println("Enter order ID to cancel: ");
                    int orderId = scanner.nextInt();
                    scanner.nextLine();
                    orderManager.cancelOrder(orderId);
                    break;
                    
                case 8:
                	  System.out.println("Shutting down order processing...");
                      orderManager.shutdown();
                      System.out.println("Exiting the system. Goodbye!");
                      scanner.close();
                      System.exit(0);
                    
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}