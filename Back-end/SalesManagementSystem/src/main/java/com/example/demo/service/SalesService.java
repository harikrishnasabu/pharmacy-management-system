package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dao.SalesRepository;
import com.example.demo.model.Order;
import com.example.demo.model.OrderProducts;
import com.example.demo.model.Product;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
@Service
public class SalesService{
	@Autowired
	 SalesRepository salesRepository;
	@Autowired
	OrderProductService orderProductService;
	@Autowired
	private EurekaClient eurekaClient;

	//-------------------------------------Adding a new Sales Order-----------------------------------------------
	public Order addOrder(Order order) {
		List<OrderProducts> orderProducts=order.getOrderProducts();
		order.setSoId(order.getSoId());
		String soId=salesRepository.save(order).getSoId();
		double sum=0;
		int totalQuantity=0;
		order.setSoDate(order.getSoDate());
		order.setCustomerName(order.getCustomerName());
		order.setCustomerAddress(order.getCustomerAddress());
		order.setCustomerEmail(order.getCustomerEmail());
		order.setCustomerPhone(order.getCustomerPhone());
		order.setSoStatus(order.getSoStatus());
		order.setSellerId(order.getSellerId());
		for(OrderProducts product:orderProducts) {
			Product productDetail=getProductDetails(product.getProductId());
		product.setOrderId(soId);
		product.setPrice(productDetail.getpSellingPrice());
		product.setProductId(product.getProductId());
		product.setQuantity(product.getQuantity());
		totalQuantity+=product.getQuantity();
		sum+=productDetail.getpSellingPrice()*product.getQuantity();
		productDetail.setpQuantity(productDetail.getpQuantity()-product.getQuantity());
		saveProductDetails(productDetail);
		orderProductService.saveOrderProducts(product);
		}
		order.setTotalQuantity(totalQuantity);
		order.setTax(sum*0.15);
		order.setTotal(sum+order.getTax());
		order.setSubTotal(sum);
		salesRepository.save(order);
		return order;
	}
	//-------------------------------------Deleting a Sales Order-----------------------------------------------
	public void deleteOrder(String id) {
		// TODO Auto-generated method stub
		Optional<Order> order=salesRepository.findById(id);
		 if(order.isPresent()) {
			 List<OrderProducts> orderProducts=orderProductService.getOrderProducts(id);
			 for(OrderProducts product:orderProducts) {
					Product productDetail=getProductDetails(product.getProductId());
					productDetail.setpQuantity(productDetail.getpQuantity()+product.getQuantity());
					saveProductDetails(productDetail);
				}
		salesRepository.deleteById(id);
		 }
	}
	//------------------------------------Fetching Sales Order----------------------------------------------------------------------------------
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return salesRepository.findAll();
	}
	//--------------------------------------------------Search Sales Order by Id---------------------------------------------------------------------
	public Optional<Order> getOrderById(String id) {
		// TODO Auto-generated method stub
		return salesRepository.findById(id);
	}
	//--------------------------------------------------Search Sales Order by Name---------------------------------------------------------------------
	public Optional<Order> getOrderByCustomerName(String name)
	{
		return salesRepository.findByCustName(name);
	}
	//------------------------------------------------Updating Sales Order--------------------------------------------------------------------------------
	public Order updateOrder(String id, Order orderDetails) {
		// TODO Auto-generated method stub
		Order order=salesRepository.getOne(id);
		orderDetails.setSoId(id);
		order.setSoId(orderDetails.getSoId());
		List<OrderProducts> orderProducts=orderDetails.getOrderProducts();
		double sum=0;
		int totalQuantity=0;
		order.setSoId(orderDetails.getSoId());
		salesRepository.save(order);
		order.setSoDate(orderDetails.getSoDate());
		order.setCustomerName(orderDetails.getCustomerName());
		order.setCustomerAddress(orderDetails.getCustomerAddress());
		order.setCustomerEmail(orderDetails.getCustomerEmail());
		order.setCustomerPhone(orderDetails.getCustomerPhone());
		order.setSoStatus(orderDetails.getSoStatus());
		order.setSellerId(orderDetails.getSellerId());
		for(OrderProducts product:orderProducts) {
		orderProductService.deleteOrderProducts(id,product.getProductId());
		Product productDetail=getProductDetails(product.getProductId());
		product.setOrderId(id);
		product.setPrice(productDetail.getpSellingPrice());
		product.setProductId(product.getProductId());
		product.setQuantity(product.getQuantity());
		totalQuantity+=product.getQuantity();
		sum+=productDetail.getpSellingPrice()*product.getQuantity();
		productDetail.setpQuantity(productDetail.getpQuantity()-product.getQuantity());
		saveProductDetails(productDetail);
		orderProductService.saveOrderProducts(product);
	}
		order.setTotalQuantity(totalQuantity);
		order.setTax(sum*0.15);
		order.setTotal(sum+order.getTax());
		order.setSubTotal(sum);
		order.setSubTotal(sum);
		salesRepository.save(order);
		return order;
		
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
	//---------------------------------Getting Orders according to Order Date------------------------------
	public List<Order> getReport(Date from, Date to) {
		// TODO Auto-generated method stub
		List<Order> orders=salesRepository.findByOrderDate(from, to);
		return orders;		
	}
}
