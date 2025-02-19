package src.business.concretes;

import java.util.*;

import src.business.abstracts.ICustomerService;
import src.dataAccess.CustomerDatabaseExample;
import src.entity.concretes.Customer;
import src.entity.concretes.Order;
import src.entity.concretes.Product;

public class CustomerManager implements ICustomerService {
	private List<Customer> customers = new ArrayList<>(CustomerDatabaseExample.getAllCustomers());
	private Scanner sc = new Scanner(System.in);

	@Override
	public Customer addCustomer(Customer customer) {
		System.out.println("Enter your first name: ");
		String name = sc.nextLine();

		System.out.println("Enter your surname: ");
		String surname = sc.nextLine();

		System.out.println("Enter e-mail address: ");
		String mail = sc.nextLine();

		System.out.println("Enter phone number: ");
		String phone = sc.nextLine();

		Customer addedCustomer = new Customer(name, surname, mail, phone);
		customers.add(addedCustomer);

		// New customer is added into db
		CustomerDatabaseExample.addCustomer(addedCustomer);

		System.out.println("You have an account now! " + addedCustomer.getName() + " " + addedCustomer.getSurname());
		return addedCustomer;
	}

	@Override
	public Customer deleteCustomer(int id) {
		Customer toDelete = customers.stream().filter(c -> c.getCustomerId() == id).findFirst().orElse(null);
		if (toDelete != null) 
		{
			customers.remove(toDelete);
			System.out.println("Customer deleted: " + toDelete.getName() + " " + toDelete.getSurname());
			return toDelete;
		} 
		else 
		{
			System.out.println("Customer not found.");
			return null;
		}
	}

	@Override
	public List<Order> orderHistory(int customerId) {
	    Customer customer = getCustomerById(customerId);
	    if (customer != null) 
	    {
	        System.out.println("Order history for " + customer.getName() + " " + customer.getSurname() + ":");
	        System.out.println("-------------------------------------------------------------------------------");
	        System.out.printf("%-10s %-20s %-20s %-10s%n", "Order ID", "Product", "Status", "Quantity");
	        System.out.println("-------------------------------------------------------------------------------");

	        List<Order> completedOrders = new ArrayList<>();

	        for (Order order : customer.getOrderHistory()) {
	            if (!order.getStatus().equals("Completed")) 
	            {
	                completedOrders.add(order);
	                
	                Set<Product> uniqueProducts = new HashSet<>(order.getProducts());//find unique products
	                // print products for ever unique order
	                for (Product product : uniqueProducts) 
	                {

	                	int orderedQuantity = 0;//to reach product quantity that's ordered
                        for (Product p : order.getProducts()) 
                        {
                            if (p.getId() == product.getId()) 
                            {
                                orderedQuantity++;
                            }
                        }

	                	System.out.printf("%-10d %-20s %-20s %-10d ",
	                            order.getOrderId(),
	                            product.getProductName(),
	                            order.getStatus(),
	                            orderedQuantity);
	                }
	            }
	        }

	        System.out.println(" ");
	        System.out.println("-------------------------------------------------------------------------------");
	        return completedOrders;
	    } 
	    else 
	    {
	        System.out.println("Customer not found.");
	        return Collections.emptyList();
	    }
	}


	@Override
	public Customer getCustomerById(int customerId) {
		return customers.stream().filter(c -> c.getCustomerId() == customerId).findFirst().orElse(null);
	}

	public Customer getCustomerByNameAndSurname(String name, String surname) {
		return customers.stream()
				.filter(c -> c.getName().equalsIgnoreCase(name) && c.getSurname().equalsIgnoreCase(surname)).findFirst()
				.orElse(null);
	}

	@Override
	public List<Customer> displayAllCustomers() {
		if (customers.isEmpty()) {
			System.out.println("No customer information available.");
			return new ArrayList<>(); 
		}

		System.out.println("--------------------------------------------------");
		System.out.printf("%-5s %-20s %-10s %-15s %-30s\n", "ID", "Name", "Surname", "E-mail Address", "Phone");
		System.out.println("--------------------------------------------------");

		for (Customer customer : customers) {
			System.out.printf("%-5d %-20s %-20s %-25s %-15s\n", customer.getCustomerId(), customer.getName(),
					customer.getSurname(), customer.getMail(), customer.getPhone());
		}

		System.out.println("--------------------------------------------------");

		return customers; 
	}
}