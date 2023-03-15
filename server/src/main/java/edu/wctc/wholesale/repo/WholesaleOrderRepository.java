package edu.wctc.wholesale.repo;

import edu.wctc.wholesale.Entity.WholesaleOrder;
import org.springframework.data.repository.CrudRepository;

public interface WholesaleOrderRepository extends CrudRepository<WholesaleOrder, Integer> {
}
