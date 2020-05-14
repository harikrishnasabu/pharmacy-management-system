package com.example.demo.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dao.OrderProductRepository;
import com.example.demo.dao.PurchaseORderRepository;
import com.example.demo.model.OrderProduct;
import com.example.demo.model.Product;
import com.example.demo.model.PurchaseOrder;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

@Service
public class PurchaseOrderService {

	@Autowired
	PurchaseORderRepository purchaseOrderRepository;
	
	@Autowired
	OrderProductService orderProductService;
	
	@Autowired
	private EurekaClient eurekaClient;
	
	//--------------------Method for Insert Order---------------------------------
	public void createPurchaseOrder(PurchaseOrder purchaseOrder) {
		
		List<OrderProduct> orderProducts = purchaseOrder.getOrderProduct();
		purchaseOrder.setPoId(purchaseOrder.getPoId());
		String poId=purchaseOrderRepository.save(purchaseOrder).getPoId();
		
		double sum = 0;
		int totalQuantity=0;
		purchaseOrder.setDate(purchaseOrder.getDate());
		purchaseOrder.setStatus(purchaseOrder.getStatus());
		purchaseOrder.setSupplierId(purchaseOrder.getSupplierId());
		
		for(OrderProduct product:orderProducts) {
			Product productDetail = getProductDetails(product.getProductId());
			product.setOrderId(poId);
			product.setProductId(product.getProductId());
			product.setPrice(productDetail.getpCostPrice());
			product.setQuantity(product.getQuantity());
			totalQuantity+=product.getQuantity();
			sum+=productDetail.getpCostPrice()*product.getQuantity();
			productDetail.setpQuantity(productDetail.getpQuantity()-product.getQuantity());
			saveProductDetails(productDetail);
			orderProductService.saveOrderProducts(product);
		}
		purchaseOrder.setTotalQuantity(totalQuantity);
		purchaseOrder.setSubTotal(sum);
		purchaseOrder.setTax(sum*0.15);
		purchaseOrder.setTotal(sum+purchaseOrder.getTax());
		//purchaseOrder.setTotal(sum);
		purchaseOrderRepository.save(purchaseOrder);
	}
	//---------------------Method for Delete order by id-------------------------------
	public void deleteOrderProduct(String id) {
		Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(id);
		if(purchaseOrder.isPresent()) {
			List<OrderProduct> orderProduct = orderProductService.getOrderProducts(id);
			for(OrderProduct orderProducts:orderProduct) {
				Product productDetails = getProductDetails(orderProducts.getProductId());
				productDetails.setpQuantity(productDetails.getpQuantity()+orderProducts.getQuantity());
				saveProductDetails(productDetails);
			}
			purchaseOrderRepository.deleteById(id);
		}
	}
	
	//-----------------------Method for get all purchase order product list--------------------------------
		public List<PurchaseOrder> getAllProducts(){
			return purchaseOrderRepository.findAll();
		}
		
	//-----------------------Method for get order by id----------------------------------
		public Optional<PurchaseOrder> getallOrderById(String id){
			return purchaseOrderRepository.findById(id);
		}
	//-------------------Method for update order---------------------------------------
	public void updatePurchaseOrder(String id,PurchaseOrder purchase ){
		
		PurchaseOrder purchaseOrder = purchaseOrderRepository.getOne(id);
		purchase.setPoId(id);
		purchaseOrder.setPoId(purchase.getPoId());
		
		List<OrderProduct> orderProducts = purchase.getOrderProduct();	
		
		double sum=0;
		int totalQuantity =0;
		
		purchaseOrder.setPoId(purchase.getPoId());
		purchaseOrderRepository.save(purchaseOrder);
		
		purchaseOrder.setDate(purchase.getDate());
		purchaseOrder.setStatus(purchase.getStatus());
		purchaseOrder.setSupplierId(purchase.getSupplierId());
		
		
		for(OrderProduct product:orderProducts) {
			
			orderProductService.deleteOrderProduct(id, product.getProductId());
			
			Product productDetail = getProductDetails(product.getProductId()); 
		
			productDetail.setpQuantity(productDetail.getpQuantity()+product.getQuantity());
			
			product.setOrderId(id);
			product.setProductId(product.getProductId());
			product.setPrice(productDetail.getpCostPrice());
			product.setQuantity(product.getQuantity());
			totalQuantity+=product.getQuantity();
			sum+=productDetail.getpSellingPrice()*product.getQuantity();
			productDetail.setpQuantity(productDetail.getpQuantity()-product.getQuantity());
			saveProductDetails(productDetail);
			orderProductService.saveOrderProducts(product);
		}
		purchaseOrder.setTotalQuantity(totalQuantity);
		purchaseOrder.setSubTotal(sum);
		purchaseOrder.setTax(sum*0.15);
		purchaseOrder.setTotal(sum+purchaseOrder.getTax());
		//purchaseOrder.setTotal(sum);
		purchaseOrderRepository.save(purchaseOrder);
	}
	
	
	//-------------------------------------------------Getting details of product-------------------------------------------------------------------------------
		public Product getProductDetails(int productId) {
			Application application = eurekaClient.getApplication("product-managment-service");
	        InstanceInfo instanceInfo = application.getInstances().get(0);
	        String uri = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() +"/product/searchproduct/" + productId;
	        System.out.println("URL" + uri);
			
			//final String uri = "http://localhost:8762/product-managment-service/product/searchproduct/"+productId;
			    RestTemplate restTemplate = new RestTemplate();
			    Product result = restTemplate.getForObject(uri, Product.class);   
			    System.out.println(result);
			    return result;
		}
		//-------------------------------------------------Save details of product-------------------------------------------------------------------------------
		public void saveProductDetails(Product product) {
			Application application = eurekaClient.getApplication("product-managment-service");
	        InstanceInfo instanceInfo = application.getInstances().get(0);
	        String uri = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() +"product/addproduct";
	        System.out.println("URL" + uri);
			//final String uri = "http://localhost:8009/product/addproduct";
		    RestTemplate restTemplate = new RestTemplate();
		    restTemplate.postForObject(uri,product, Product.class);   
		}
		//---------------------------------Getting Products according to Order Date------------------------------
		public List<PurchaseOrder> getReport(Date from, Date to) {
			// TODO Auto-generated method stub
			List<PurchaseOrder> orders=purchaseOrderRepository.findByOrderDate(from, to);
			return orders;		
		}
}
