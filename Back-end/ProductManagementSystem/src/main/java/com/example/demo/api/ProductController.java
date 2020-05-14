package com.example.demo.api;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;


@RestController
@RequestMapping("product")  
public class ProductController{
	private final ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService=productService;	
	}
	@CrossOrigin
	@PostMapping("/addproduct")
	public void addProduct(@RequestBody Product product) {
		productService.addProduct(product);
	}
	@CrossOrigin
	@DeleteMapping(value="/deleteproduct/{id}")
	public void deleteProduct(@PathVariable int id) {
		productService.deleteProduct(id);
	}
	@CrossOrigin
	@PutMapping(value="/updateproduct/{id}", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody Product productDetails) {
		if(productService.updateProduct(id,productDetails).equals(null))
		{
			return new ResponseEntity<>("Not Updated",HttpStatus.BAD_REQUEST);
		}
		else
		{
			return new ResponseEntity<>("Updated",HttpStatus.OK);
		}
		
	}
	@CrossOrigin
	@GetMapping(value="/allproducts", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getProducts(){
		return productService.getAllProducts();
	}
	@CrossOrigin
	@GetMapping(value="/report", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getReport(@RequestParam Date from,@RequestParam Date to){
		return productService.getReport(from,to);
		
	}
	@CrossOrigin
	@GetMapping(value="/productbysupplier", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getProductBySupplier(@RequestParam int sid){
		return productService.getProductBySupplier(sid);	
	}
	@CrossOrigin
	@GetMapping(value="/searchproduct/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Optional<Product> searchProduct(@PathVariable int id){
		return productService.searchProduct(id);	
	}
	@CrossOrigin
	@GetMapping("/search/{pname}")
	public List<Product> searchForProduct(@PathVariable String pname) {
		return productService.searchProductByName(pname);
	}
	@CrossOrigin
	@GetMapping(value="/allproductsavailableforsale", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getProductsAvailable(){
		return productService.getProductsAvailable();
	}
	@CrossOrigin
	@GetMapping(value="/allproductsbyquantity", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getProductsByQuantity(){
		return productService.getProductsByQuantity();
}
	@CrossOrigin
	@GetMapping(value="/allproductsbyquantityandsupplier", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Product> searchProductsByQuantityAndSupplier(@RequestParam int sid){
		return productService.searchProductsByQuantityAndSupplier(sid);
	}
}
