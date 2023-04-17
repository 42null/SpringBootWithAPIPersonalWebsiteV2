package net.the42null.personalwebsite.repo;

import net.the42null.personalwebsite.Entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Integer> {

	Optional<Product> findByName(String name);
}
