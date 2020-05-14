package com.example.demo.api;


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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Supplier;
import com.example.demo.service.SupplierService;


@RestController
@RequestMapping("supplier")
@CrossOrigin
public class SupplierController {

	@Autowired
	SupplierService supplierService;
	//-------------------For Insert supplier-----------------------------------------
	@PostMapping("/addSupplier")
	public void createSupplier(@RequestBody Supplier supplier) {
		supplierService.createSupplier(supplier);
	}
	//--------------------For delete supplier------------------------------------------
	@DeleteMapping(value = "/delete/{id}")
	public void deleteSupplier(@PathVariable int id) {
		supplierService.deleteSupplier(id);
	}
	//--------------------For Update supplier-----------------------------------------
	@PutMapping(value = "/updateSupplier/{id}")
	public void updateSupplier(@RequestBody Supplier supplier,@PathVariable int id) {
		supplierService.updateSupplier(supplier,id);
	}
	//-------------------For list of supplier----------------------------------------- 
	@GetMapping(value = "/allSupplier", produces =MediaType.APPLICATION_JSON_VALUE)
	public List<Supplier> getSuppliers(){
		return supplierService.getSuppliers();
	}
	//-------------------Search supplier by id-----------------------------------------
	@GetMapping(value = "/searchSupplier/{id}", produces =MediaType.APPLICATION_JSON_VALUE)
	public Optional<Supplier> getSupplierById(@PathVariable int id){
		return supplierService.getSupplierById(id);
	}
	//------------------Search Supplier by name------------------------------------------
	@GetMapping(value = "/searchSupplierName/{sName}", produces =MediaType.APPLICATION_JSON_VALUE)
	public List<Supplier> getSupplierName(@PathVariable String supplierName){
		return supplierService.getSupplierByName(supplierName);
	}
}
