//package net.the42null.personalwebsite.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.github.fge.jsonpatch.JsonPatch;
//import com.github.fge.jsonpatch.JsonPatchException;
//import net.the42null.personalwebsite.Entity.WholesaleOrder;
//import net.the42null.personalwebsite.Service.CustomerServiceImpl;
//import net.the42null.personalwebsite.Service.ProductServiceImpl;
//import net.the42null.personalwebsite.Service.WholesaleOrderService;
//import net.the42null.personalwebsite.dto.DtoOrder;
//import net.the42null.personalwebsite.exception.ResourceNotFoundException;
//import net.the42null.personalwebsite.repo.WholesaleOrderRepository;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@RestController
//@RequestMapping("/api/orders")
//@CrossOrigin(origins="http://localhost:63342")
//public class WholesaleOrderController {
//
//    @Autowired
//    private WholesaleOrderService wholesaleOrderService;
//
//    @Autowired
//    private WholesaleOrderRepository orderRepository;
//
//    @Autowired
//    private CustomerServiceImpl customerService;
//
//    @Autowired
//    private ProductServiceImpl productService;
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//    private DtoOrder convertToDto(WholesaleOrder wholesaleOrder) {
//        DtoOrder dtoOrder = new DtoOrder();
//        dtoOrder.setCustomerName(wholesaleOrder.getCustomer().getName());
//        dtoOrder.setPurchaseDate(wholesaleOrder.getPurchaseDate());
//        dtoOrder.setPurchaseOrderNumber(wholesaleOrder.getPurchaseOrderNumber());
//        dtoOrder.setProductName(wholesaleOrder.getProduct().getName());
//        dtoOrder.setTerms(wholesaleOrder.getTerms());
//        dtoOrder.setShippedDate(wholesaleOrder.getShippedDate());
//        dtoOrder.setProductCost(wholesaleOrder.getProduct().getCost());
//
////        System.out.println(">>\n"+dtoOrder.getCustomerName());
////        System.out.println(dtoOrder.getPurchaseDate());
////        System.out.println(dtoOrder.getPurchaseOrderNum());
////        System.out.println(dtoOrder.getProductName());
////        System.out.println(dtoOrder.getTerms());
////        System.out.println(dtoOrder.getShippedDate());
////        System.out.println(dtoOrder.getProductCost());
//
//        return dtoOrder;
//    }
//
////    private WholesaleOrder convertToEntity(DtoOrder dtoOrder){
////        WholesaleOrder wholesaleOrder = new WholesaleOrder();
////        wholesaleOrder.setTerms(dtoOrder.getTerms());
////        wholesaleOrder.setCustomer(dtoOrder.getCustomerName());
////        wholesaleOrder.setProduct(dtoOrder.getProductName());
////        wholesaleOrder.setPurchaseDate(dtoOrder.getPurchaseDate());
////        wholesaleOrder.setShippedDate(dtoOrder.getShippedDate());
////        return wholesaleOrder;
////    }
//    private WholesaleOrder convertToEntity(DtoOrder dtoOrder) throws ResourceNotFoundException {
//        WholesaleOrder wholesaleOrder = modelMapper.map(dtoOrder, WholesaleOrder.class);
//        wholesaleOrder.setCustomer(customerService.getCustomerByName(dtoOrder.getCustomerName()));
//        return wholesaleOrder;
//    }
//
//
//    @GetMapping
//    public List<DtoOrder> getAllOrders() {
//        List<DtoOrder> list = new ArrayList<>();
////        orderRepository.findAll().forEach(list::add);
//        orderRepository.findAll().forEach(order -> list.add(convertToDto(order)));
//
////        List<WholesaleOrder> wholesaleOrderList = wholesaleOrderService.getAllOrders();
////        for(WholesaleOrder wholesaleOrder: wholesaleOrderList){
////            list.add(convertToDto(wholesaleOrder));
////        }
//        return list;
//    }
//
//    @GetMapping("/{orderId}")
//    public DtoOrder getOneOrderById(@PathVariable String orderId){
//        try {
//            int id = Integer.parseInt(orderId);
//            DtoOrder order = convertToDto(wholesaleOrderService.getOrderById(id));
//            return order;
//        } catch (NumberFormatException ex) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID must be numeric");
//        } catch (ResourceNotFoundException ex) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
//        }
//    }
//
//    @DeleteMapping("/{orderId}")
//    public boolean deleteOrderById(@PathVariable String orderId){
//        try {
//            int id = Integer.parseInt(orderId);
//            wholesaleOrderService.deleteOrder(id);
//            return true;
//        } catch (NumberFormatException ex) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID must be numeric");
//        } catch (ResourceNotFoundException ex) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
//        }
//    }
//
//    @PostMapping
//    public DtoOrder createOrder(@RequestBody DtoOrder newOrder) {
//        try {
//            wholesaleOrderService.addOrder(convertToEntity(newOrder));
//        } catch (NumberFormatException ex) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID must be numeric");
//        } catch (ResourceNotFoundException ex) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
//        }
//        return newOrder;
//    }
//
//    @PatchMapping("/{orderId}")
//    public DtoOrder patchOrder(@PathVariable String orderId,
//                                @RequestBody JsonPatch patch) {
//        try {
//            int id = Integer.parseInt(orderId);
//            return convertToDto(wholesaleOrderService.patch(id, patch));
//        } catch (NumberFormatException e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                    "Order ID must be a number", e);
//        } catch (ResourceNotFoundException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
//                    e.getMessage(), e);
//        } catch (JsonPatchException | JsonProcessingException e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                    "Invalid patch format: " + e.getMessage(), e);
//        }
//    }
//
////    @PutMapping
////    public StockPurchase updateStockPurchase(@RequestBody StockPurchase order) {
////        try {
////            return service.update(order);
////        } catch (ResourceNotFoundException e) {
////            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
////                    e.getMessage(), e);
////        }
////    }
//}
