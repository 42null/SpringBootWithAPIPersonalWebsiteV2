package net.the42null.personalwebsite.Service;

import net.the42null.personalwebsite.Entity.Product;
import net.the42null.personalwebsite.exception.ResourceNotFoundException;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    boolean addProduct(Product product);

    boolean deleteProduct(int productId);

    Product getProductById(int productId) throws ResourceNotFoundException;
}
