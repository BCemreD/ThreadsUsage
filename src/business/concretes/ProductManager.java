package src.business.concretes;

import java.util.*;
import java.util.stream.Collectors;

import src.business.abstracts.IProductService;
import src.dataAccess.DatabaseExample;
import src.entity.concretes.Product;

public class ProductManager implements IProductService {

    private List<Product> products = new ArrayList<>(DatabaseExample.getAllProducts());
    private Scanner s = new Scanner(System.in);

    @Override
    public Product add(Product product) {
        System.out.println("Product name: ");
        String productName = s.nextLine();

        System.out.println("Enter price: ");
        double price = s.nextDouble();

        System.out.println("Enter the stock quantity: ");
        int stockQuantity = s.nextInt();
        s.nextLine();

        System.out.println("Product description: ");
        String description = s.nextLine();

        Product addedProduct = new Product(productName, price, stockQuantity, description);
        products.add(addedProduct);

        // to add new product into db
        DatabaseExample.addProduct(addedProduct);

        System.out.println("Product's added successfully: " + addedProduct.getProductName());
        return addedProduct;
    }

    @Override
    public Product remove(int id) {
        Product toRemove = products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        if (toRemove != null) 
        {
            products.remove(toRemove);
            System.out.println("Removed: " + toRemove.getProductName());
            return toRemove;
        } 
        else 
        {
            System.out.println("Product not found.");
            return null;
        }
    }

    @Override
    public void update(Product product) {
        Product existingProduct = products.stream().filter(p -> p.getId() == product.getId()).findFirst().orElse(null);
        if (existingProduct != null) 
        {
            System.out.println("Enter new price (or press Enter to keep current price): ");
            String priceInput = s.nextLine();
            if (!priceInput.isEmpty()) 
            {
                double newPrice = Double.parseDouble(priceInput);
                existingProduct.setPrice(newPrice);
            }

            System.out.println("Enter new description (or press Enter to keep current description): ");
            String descriptionInput = s.nextLine();
            if (!descriptionInput.isEmpty())
            {
                existingProduct.setDescription(descriptionInput);
            }

            System.out.println("Product updated successfully.");
        } 
        else 
        {
            System.out.println("Product not found.");
        }
    }

    @Override
    public List<Product> sortByPrice() {
        return products.stream().sorted(Comparator.comparingDouble(Product::getPrice)).collect(Collectors.toList());
    }

    @Override
    public Product getById(int id) {
        return products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }
    
    @Override
    public void updateStockQuantity(Product product, int quantity) {
        product.setStockQuantity(product.getStockQuantity() - quantity);
    }
    
    @Override
    public List<Product> displayAllProducts() { 
    	if(products.isEmpty()) {
    		System.out.println("No product information available.");
    		return new ArrayList<>();
    	}
    	
    	System.out.println("--------------------------------------------------");
        System.out.printf("%-5s %-20s %-10s %-15s\n", "ID", "Name", "Price", "Description");
        System.out.println("--------------------------------------------------");
        
        for (Product product : products) {
        	System.out.printf("%-5d %-20s %-20f %-10d %-25s\n",
                    product.getId(), product.getProductName(), product.getPrice(), product.getStockQuantity(),
                    product.getDescription());
        }
        
        System.out.println("--------------------------------------------------");
    	
    	return products;
    }
}