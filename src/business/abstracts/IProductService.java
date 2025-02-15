package src.business.abstracts;

import java.util.List;

import src.entity.concretes.Product;

public interface IProductService {
	
	Product add(Product product);
	Product remove(Product product);
	double changePrice(Product product);
	List<Product> getAllProducts();
	List<Product> sortByPrice();

}
