package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dao.OrderProductRepository;
import com.example.demo.model.OrderProduct;
import com.example.demo.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

@Service
public class OrderProductService {
	@Autowired
	OrderProductRepository orderProductRepository;
	
	@Autowired
	private EurekaClient eurekaClient;
	
	public void saveOrderProducts(OrderProduct orderProduct)
	{
		orderProductRepository.save(orderProduct);
	}
	public void deleteOrderProduct(String orderId, int productId) {
		Product productDetail=getProductDetails(productId);
		OrderProduct orderProduct = orderProductRepository.findOrderProducts(orderId, productId);
		productDetail.setpQuantity(productDetail.getpQuantity()-orderProduct.getQuantity());
		saveProductDetails(productDetail);
		orderProductRepository.deleteOrderProducts(orderId, productId);
	}
	public List<OrderProduct> getOrderProducts(String orderId)
	{
		return orderProductRepository.findOrderProductsByOrder(orderId);
	}
	//-------------------------------------------------Getting details of product-------------------------------------------------------------------------------
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	public void saveProductDetails(Product product) {
		Application application = eurekaClient.getApplication("product-managment-service");
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String uri = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() +"product/addproduct";
        System.out.println("URL" + uri);
		//final String uri = "http://localhost:8009/product/addproduct";
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.postForObject(uri,product, Product.class);    
		}
}
