package edu.wctc.wholesale.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import edu.wctc.wholesale.Entity.WholesaleOrder;
import edu.wctc.wholesale.exception.ResourceNotFoundException;
import edu.wctc.wholesale.repo.WholesaleOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WholesaleOrderServiceImpl implements WholesaleOrderService {

    @Autowired
    private WholesaleOrderRepository wholesaleOrderRepo;

    private ObjectMapper objectMapper;

    @Override
    public List<WholesaleOrder> getAllOrders() {
        List<WholesaleOrder> list = new ArrayList<>();
        wholesaleOrderRepo.findAll().forEach(list::add);
        return list;
    }

    @Override
    public boolean addOrder(WholesaleOrder wholesaleOrder) {
        if(!wholesaleOrderRepo.existsById(wholesaleOrder.getId())){
            wholesaleOrderRepo.save(wholesaleOrder);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteOrder(int orderId) throws ResourceNotFoundException {
        if(wholesaleOrderRepo.findById(orderId).isPresent()){
            wholesaleOrderRepo.deleteById(orderId);
            return true;
        }else{
            throw new ResourceNotFoundException("Order", "id", Integer.toString(orderId));
        }
    }

    @Override
    public WholesaleOrder getOrderById(int orderId) throws ResourceNotFoundException{
        Optional<WholesaleOrder> optional = wholesaleOrderRepo.findById(orderId);

        return optional.orElseThrow(() -> new ResourceNotFoundException("Order", "id", Integer.toString(orderId)));
    }

    public WholesaleOrder patch(int id, JsonPatch patch) throws ResourceNotFoundException, JsonPatchException, JsonProcessingException {
        WholesaleOrder existingStudent = getOrderById(id);
        JsonNode patched = patch.apply(objectMapper
                                               .convertValue(existingStudent, JsonNode.class));
        WholesaleOrder wholesaleOrder = objectMapper.treeToValue(patched, WholesaleOrder.class);
        wholesaleOrderRepo.save(wholesaleOrder);
        return wholesaleOrder;
    }
}
