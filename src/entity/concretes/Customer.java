package src.entity.concretes;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer {

	private static final AtomicInteger nextId = new AtomicInteger(1);
	private int customerId;
	private String name;
	private String surname;
	private String mail;
	private String phone;
	private List<Order> orderHistory = new ArrayList<Order>();

	public Customer(String name, String surname, String mail, String phone, List<Order> orderHistory) {
		super();
		this.customerId = nextId.getAndIncrement();
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.phone = phone;
		this.orderHistory = orderHistory;
	}

	// polymorphism
	public Customer(String name, String surname, String mail, String phone) {
		this.customerId = nextId.getAndIncrement();
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.phone = phone;
	}
	
	

	public int getCustomerId() {
		return customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Order> getOrderHistory() {
		return orderHistory;
	}

	public void setOrderHistory(List<Order> orderHistory) {
		this.orderHistory = orderHistory;
	}

	 @Override
	    public String toString() {
	        return "Customer{" +
	                "customerId=" + customerId +
	                ", name='" + name + '\'' +
	                ", surname='" + surname + '\'' +
	                ", mail='" + mail + '\'' +
	                ", phone='" + phone + '\'' +
	                ", orderHistory=" + orderHistory +
	                '}';
	    }

}
