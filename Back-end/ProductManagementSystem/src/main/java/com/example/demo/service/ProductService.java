package com.example.demo.service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductRepository;
import com.example.demo.model.Product;

@Service
public class ProductService {
@Autowired
 private ProductRepository productRepository;

//-------------------------------------Adding a new Product-----------------------------------------------
public void addProduct(Product product) {
	product.setpName(product.getpName());
	product.setpDescription(product.getpDescription());
	product.setpManufactureDate(product.getpManufactureDate());
	product.setpExpiryDate(product.getpExpiryDate());
	product.setpQuantity(product.getpQuantity());
	product.setpSellingPrice(product.getpSellingPrice());
	product.setpCostPrice(product.getpCostPrice());
	product.setsId(product.getsId());
	productRepository.save(product);
}

//-----------------------------------Finding a Product By Id----------------------------------------------
public Optional<Product> getProduct(int id) {
	return productRepository.findById(id);
}

//------------------------------------Deleting a Product--------------------------------------------------
 public void deleteProduct(int id) {
	 Optional<Product> product=getProduct(id);
	 if(product.isPresent()) {
	 productRepository.deleteById(id);
	 }
 }
 
//---------------------------------Updating a Product-----------------------------------------------------
public ResponseEntity<Product> updateProduct(int id, Product productDetails) {
	// TODO Auto-generated method stub
	Product product=productRepository.getOne(id);
	product.setpName(productDetails.getpName());
	product.setpDescription(productDetails.getpDescription());
	product.setpManufactureDate(productDetails.getpManufactureDate());
	product.setpExpiryDate(productDetails.getpExpiryDate());
	product.setpQuantity(productDetails.getpQuantity());
	product.setpSellingPrice(productDetails.getpSellingPrice());
	product.setpCostPrice(productDetails.getpCostPrice());
	product.setsId(productDetails.getsId());
	final Product updatedProduct=productRepository.save(product);
return ResponseEntity.ok(updatedProduct);
}

//----------------------------------Getting a List of All Products----------------------------------------
public java.util.List<Product> getAllProducts() {
	// TODO Auto-generated method stub
	return productRepository.findAll();
}

//---------------------------------Getting Products according to Expiry Date------------------------------
public List<Product> getReport(Date from, Date to) {
	// TODO Auto-generated method stub
	List<Product> products=productRepository.findByExpiryDate(from, to);
	return products;		
}

//--------------------------------Search Product By Supplier----------------------------------------------
public List<Product> getProductBySupplier(int sid) {
	// TODO Auto-generated method stub
	List<Product> products=productRepository.findBySupplier(sid);
	return products;
}

//-------------------------------Search Product By Id-----------------------------------------------------
public Optional<Product> searchProduct(int id) {
	return productRepository.findById(id);
}

//-------------------------------Search Product By Name-----------------------------------------------------
public List<Product> searchProductByName(String pname) {
	return productRepository.findByProductName(pname);
}
//---------------------------------Get Products with Quantity Greater then zero-------------------------------------------------------------------------------
public List<Product> getProductsAvailable() {
	// TODO Auto-generated method stub
	return productRepository.findAvailableProduct();
}
//---------------------------------------------------Get Products with Quantity less then 50-------------------------------------------------------------------
public List<Product> getProductsByQuantity() {
	// TODO Auto-generated method stub
	return productRepository.findProductsByQuantity();
}
//-------------------------------------------Get Products with Quantity less then 50 and according to Supplier-----------------------------------------------------
public List<Product> searchProductsByQuantityAndSupplier(int sid) {
	// TODO Auto-generated method stub
	return productRepository.findProductsByQuantityAndSupplier(sid);
}
}
