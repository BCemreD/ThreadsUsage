package src.business.abstracts;

import java.util.List;

import src.entity.concretes.Order;

public interface IOrderService {
	
	Order cancel();
	String process();
	List<Order> displayInfo();

}
