package src.business.abstracts;

import java.util.List;

import src.entity.concretes.Product;

public interface IProductService {
    Product add(Product product);
    Product remove(int id); 
    void update(Product product); 
    //List<Product> getAllProducts();
    List<Product> sortByPrice();
    Product getById(int id); 
    List<Product> displayAllProducts();
    void updateStockQuantity(Product product, int quantity);
}