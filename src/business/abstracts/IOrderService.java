package src.business.abstracts;

import java.util.List;

import src.entity.concretes.Customer;
import src.entity.concretes.Order;
import src.entity.concretes.Product;

public interface IOrderService {
    Order addOrder(Customer customer, List<Product> products); // customer and product info are needed
    Order cancelOrder(int orderId);
    String orderProcess();
}