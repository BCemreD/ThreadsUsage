package entity.concretes;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	
	private int customerId;
	private String firstName;
	private String surname;
	private String mail;
	private String phone;
	private List <Product> orderHistory = new ArrayList();

}
