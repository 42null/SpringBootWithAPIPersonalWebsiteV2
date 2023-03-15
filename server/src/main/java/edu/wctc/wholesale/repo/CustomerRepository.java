package edu.wctc.wholesale.repo;

import edu.wctc.wholesale.Entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

	Optional<Customer> findByName(String name);
}
