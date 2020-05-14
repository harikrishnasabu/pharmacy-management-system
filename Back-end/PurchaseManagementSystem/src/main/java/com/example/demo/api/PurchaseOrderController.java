package com.example.demo.api;



import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.PurchaseOrder;
import com.example.demo.service.PurchaseOrderService;

@RestController
@RequestMapping("purchaseOrder")
@CrossOrigin
public class PurchaseOrderController {
private final PurchaseOrderService purchaseOrderService;
	
	@Autowired
	 public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
		// TODO Auto-generated constructor stub
		this.purchaseOrderService=purchaseOrderService;
	}
	//-----------------------For Insert in to Order---------------------------
	@PostMapping(value = "/addOrderProduct",consumes = "application/json")
	public void createPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder){
		purchaseOrderService.createPurchaseOrder(purchaseOrder);
	}
	//----------------------For Delete Order----------------------------------
	@DeleteMapping(value = "/deleteOrderProduct/{id}")
	public void deletePurchaseOrderByid(@PathVariable String id) {
		purchaseOrderService.deleteOrderProduct(id);
	}
	//----------------------For All list of Orders-----------------------------
	@GetMapping(value = "/allOrderProduct", produces =MediaType.APPLICATION_JSON_VALUE)
	public List<PurchaseOrder> getAllProducts(){
		return purchaseOrderService.getAllProducts();
	}
	//---------------------For Search by id of orders--------------------------
	@GetMapping(value = "/orderProductById/{id}", produces =MediaType.APPLICATION_JSON_VALUE)
	public Optional<PurchaseOrder> getallOrderById(@PathVariable String id){
		return purchaseOrderService.getallOrderById(id);
	}
	//----------------------For Update Order-----------------------------------
	@PutMapping(value = "/updateOrderProduct/{id}")
	public void updatePurchaseOrder(@PathVariable String id,@RequestBody PurchaseOrder purchaseOrder) {
		purchaseOrderService.updatePurchaseOrder(id,purchaseOrder);
	}
	
	//----------------------For Reports from Date(Find by Date)------------------
	@CrossOrigin
	@GetMapping(value="/report", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<PurchaseOrder> getReport(@RequestParam Date from, @RequestParam Date to){
		return purchaseOrderService.getReport(from,to);	
	}
	
}
