package src.business.concretes;

import src.entity.concretes.Order;

public class OrderProcessor implements Runnable{
	
	private Order order; //to control every order threads
	

	public OrderProcessor(Order order) {
		super();
		this.order = order;
	}


	@Override
	public void run() {
		 System.out.println("Processing order: " + order.getOrderId() + " for customer: " + 
		 order.getCustomer().getName()+ order.getCustomer().getSurname());
		 
		 try {
			 Thread.sleep(2000);//simulation of waiting period
		 }
		 catch (InterruptedException e) {
			 e.printStackTrace();
		 }
		order.setStatus("Completed");
		System.out.println("Order " + order.getOrderId() + " processed successfully!");
	}

}
