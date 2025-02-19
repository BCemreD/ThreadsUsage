package src.entity.concretes;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Order {

	private static final AtomicInteger nextId = new AtomicInteger(1);
	private int orderId;
	private int quantity;
	private List<Product> products;
	private Customer customer;
	private String status;

	public Order(List<Product> products, Customer customer, String status) {
        this.orderId = nextId.getAndIncrement();
        this.products = products;
        this.customer = customer;
        this.quantity = products.size(); // Quantity is derived from product list
        this.status = status;
    }
	public Order() {
		
	}
	public Order(List<Product> productList, List<Customer> customers2, String status2) {
		// TODO Auto-generated constructor stub
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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	 @Override
	    public String toString() {
	        return "Order{" +
	                "orderId=" + orderId +
	                ", quantity=" + quantity +
	                ", products=" + products +  // Ürün listesini de toString'e ekledim.
	                ", customer=" + customer.getName() + " " + customer.getSurname() +
	                ", status='" + status + '\'' +
	                '}';
	    }
	 
	 public List<Product> getAllProducts() {
		    return products;
		}

}
