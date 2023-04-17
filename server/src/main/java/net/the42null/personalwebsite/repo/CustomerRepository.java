package net.the42null.personalwebsite.repo;

import net.the42null.personalwebsite.Entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

	Optional<Customer> findByName(String name);
}
