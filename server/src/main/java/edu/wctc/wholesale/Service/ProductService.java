package edu.wctc.wholesale.Service;

import edu.wctc.wholesale.Entity.Product;
import edu.wctc.wholesale.exception.ResourceNotFoundException;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    boolean addProduct(Product product);

    boolean deleteProduct(int productId);

    Product getProductById(int productId) throws ResourceNotFoundException;
}
