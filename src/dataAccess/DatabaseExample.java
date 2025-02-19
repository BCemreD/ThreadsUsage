package src.dataAccess;

import java.util.ArrayList;
import java.util.List;

import src.entity.concretes.Product;

public class DatabaseExample {
	
	//creating a list for simulating a database 
	private static List<Product> products = new ArrayList<>();
	
	static {
		products.add(new Product("Laptop", 1200.0, 10, "Red, fast, good"));
		products.add(new Product("Phone", 800.0, 20, "Smart phone"));
		products.add(new Product("Tablet", 500.0, 15, "Overqualitfied"));
		products.add(new Product("Headphones", 150.0, 50, "Accessories for an adventure"));
		products.add(new Product("Monitor", 300.0, 25, "Electronics"));
	}
	
	public static List<Product> getAllProducts(){
		return products;

}
	// 
    public static void addProduct(Product product) {
        products.add(product);
    }
	
}
	

