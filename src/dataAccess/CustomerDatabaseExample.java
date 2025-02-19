package src.dataAccess;

import java.util.ArrayList;
import java.util.List;

import src.entity.concretes.Customer;
import src.entity.concretes.Product;

public class CustomerDatabaseExample {
	
	//creating a list for simulating a database 
	private static List<Customer> customers = new ArrayList<>();
	
	static {
		customers.add(new Customer ( "Ali", "Aligil", "ali@mail.com", "5487689087"));
		customers.add(new Customer ( "Ayşe", "Kıraç", "aayse@mail.com", "5486656087"));
		customers.add(new Customer ( "Selim", "Birgen", "selimb@mail.com", "5347684567"));
		customers.add(new Customer ( "Canan", "Fındık", "c.findik@mail.com", "5423478087"));

	}
	
	public static List<Customer> getAllCustomers(){
		return customers;
	}
	
	 public static void addCustomer(Customer customer) {
	        customers.add(customer);
	    }
}
