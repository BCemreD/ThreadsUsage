package src.business.concretes;

import java.util.*;
import java.util.stream.Collectors;

import src.business.abstracts.IProductService;
import src.entity.concretes.Product;

public class ProductManager implements IProductService{
	
	List<Product> products = new ArrayList();
	
	Scanner s = new Scanner(System.in);

	@Override
	public Product add(Product product) {
		
		System.out.println("Product name: ");
		String productName =  s.nextLine();
		
		System.out.println("Enter price: ");
		double price = s.nextInt();
		
		System.out.println("Enter the stock quantity: ");
		int stockQuantity = s.nextInt();
		s.nextLine(); //to remove gap
		
		System.out.println("Product description: ");
		String description = s.nextLine();
		
		Product addedProduct = new Product(productName, price, stockQuantity, description);
		
		products.add(addedProduct);

        System.out.println("Product's added successfully: " + addedProduct.getProductName());
        
		return addedProduct;
	}

	@Override
	public Product remove(Product product) {
	    System.out.println("Enter part of the product name to remove: ");
	    String input = s.nextLine().trim().toLowerCase();

	    // Filter by input
	    List<Product> matchedProducts = products.stream()
	        .filter(p -> p.getProductName().toLowerCase().contains(input))
	        .collect(Collectors.toList());

	    if (matchedProducts.isEmpty()) {
	        System.out.println("No matching products found.");
	        return null;
	    }

	    // list the matched products
	    System.out.println("Matching products:");
	    for (int i = 0; i < matchedProducts.size(); i++) {
	        System.out.println((i + 1) + ". " + matchedProducts.get(i).getProductName());
	    }

	    System.out.print("Enter the number of the product to remove: ");
	    int option;
	    try {
	        option = Integer.parseInt(s.nextLine());
	    } catch (NumberFormatException e) {
	        System.out.println("Invalid input.");
	        return null;
	    }

	    if (option >= 1 && option <= matchedProducts.size()) {
	        Product toRemove = matchedProducts.get(option - 1);
	        products.remove(toRemove);
	        System.out.println("Removed: " + toRemove.getProductName());
	        return toRemove;
	    } else {
	        System.out.println("Invalid selection.");
	        return null;
	    }
	}

	@Override
	public double changePrice(Product product) {
		
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("Enter the discount percentage (e.g., 10 for 10% off): ");
	    
	    int discountPercentage = scanner.nextInt();
	    
	    // Calculating the new price
	    double newPrice = product.getPrice() * (1 - discountPercentage / 100.0);
	    
	    // Set the new price
	    product.setPrice(newPrice);
	    
	    System.out.println("The price of " + product.getProductName() + " has been updated to: " + newPrice);
	    
	    return newPrice;
	}


	@Override
	public List<Product> getAllProducts() {
		System.out.println("Products are listed:");
		products.forEach(System.out::println);
		return products;
	}

	
	@Override
	public List<Product> sortByPrice() {
		var sortedProducts = new ArrayList<>(products); // Copied to save the original list
	    sortedProducts.sort(Comparator.comparingDouble(Product::getPrice)); // sorted by price asc.
	    return sortedProducts;
	}

}
