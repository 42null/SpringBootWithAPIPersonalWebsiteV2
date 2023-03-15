package edu.wctc.wholesale.controller;

import edu.wctc.wholesale.Entity.Product;
import edu.wctc.wholesale.Service.ProductService;
import edu.wctc.wholesale.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public List<Product> getAllProducts(){
		return productService.getAllProducts();
	}

	@GetMapping("/{productId}")
	public Product getOneProduct(@PathVariable String productId){
		try {
			int id = Integer.parseInt(productId);
			Product product = productService.getProductById(id);
			return product;
		} catch (NumberFormatException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID must be numeric");
		} catch (ResourceNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}

}
