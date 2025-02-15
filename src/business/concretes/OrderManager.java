package src.business.concretes;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import src.business.abstracts.IOrderService;
import src.entity.concretes.Order;

public class OrderManager implements IOrderService {
	
	private ExecutorService executorService = Executors.newFixedThreadPool(5);//pool w/ max 5 threads

	@Override
	public Order cancel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String process() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> displayInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void process(Order order) {
		executorService.submit(new OrderProcessor(order));
	}
	
	public void shutdown() {
		executorService.shutdown();
	}

}
