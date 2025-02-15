package src.entity.concretes;

import java.util.Random;

public class Order {
	
	private Random random = new Random();
	private int orderId;
	private int quantity;
	private Product productName;
	private Customer customerId;
	private String status;
	
	public Order(int orderId, int quantity, Product productName, Customer customerId, String status) {
		super();
		this.orderId = random.nextInt(10000)+1;
		this.quantity = quantity;
		this.productName = productName;
		this.customerId = customerId;
		this.status = status;
	}

	public int getOrderId() {
		return orderId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProductName() {
		return productName;
	}

	public void setProductName(Product productName) {
		this.productName = productName;
	}

	public Customer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
