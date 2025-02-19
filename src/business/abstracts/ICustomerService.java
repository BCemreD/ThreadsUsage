package src.business.abstracts;

import java.util.List;

import src.entity.concretes.Customer;
import src.entity.concretes.Order;

public interface ICustomerService {
    Customer addCustomer(Customer customer);
    Customer deleteCustomer(int id); 
    List<Order> orderHistory(int customerId);
    Customer getCustomerById(int customerId); 
    public List<Customer> displayAllCustomers();
}