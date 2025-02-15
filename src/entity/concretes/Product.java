package src.entity.concretes;

import java.util.Random;

public class Product {
	
	private Random random = new Random();
	private final int id;
	private String productName;
	private double price;
	private int stockQuantity;
	private String description;
	
	public Product(String productName, double price, int stockQuantity, String description) {
		super();
		this.id = random.nextInt(10000)+1;
		this.productName = productName;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@Override
	public String toString() {
	    return "Product{" +
	    		"id=" + id +
	            ", productName='" + productName + '\'' +
	            ", price=" + price +
	            ", stockQuantity=" + stockQuantity +
	            ", description='" + description + '\'' +
	            '}';
	}
}
