package edu.wctc.wholesale.Service;

import edu.wctc.wholesale.Entity.Product;
import edu.wctc.wholesale.exception.ResourceNotFoundException;
import edu.wctc.wholesale.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepo;

    @Override
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        productRepo.findAll().forEach(list::add);
        System.out.println("list ="+list);
        return list;
    }

    @Override
    public boolean addProduct(Product product) {//TODO: Make error if it already exists
        if(!productRepo.existsById(product.getProductId())){
            productRepo.save(product);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int productId) {//TODO: Make error if it already exists
        if(productRepo.findById(productId).isPresent()){
            productRepo.deleteById(productId);
            return true;
        }
        return false;
    }

    @Override
    public Product getProductById(int productId) throws ResourceNotFoundException{
        Optional<Product> optional = productRepo.findById(productId);

        return optional.orElseThrow(() -> new ResourceNotFoundException("Product", "id", Integer.toString(productId)));
    }

}
