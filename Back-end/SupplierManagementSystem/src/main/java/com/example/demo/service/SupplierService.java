 package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.SupplierRepository;
import com.example.demo.model.Supplier;

@Service
public class SupplierService {
	
	@Autowired
	SupplierRepository supplierRepository;
	//----------------------Method for Insert Supplier data---------------------------------
	public void createSupplier(Supplier supplier) {
		supplierRepository.save(supplier);
	}
	//----------------------Method for deleting supplier------------------------------------	
	public void deleteSupplier(int id) {
		Optional<Supplier> supplier = getSupplierById(id);
		if(supplier.isPresent()) {
			supplierRepository.deleteById(id);
		}
	}
	//---------------------Method for Update Supplier details------------------------------
	public void updateSupplier(Supplier newSupplier,int id) {
		
			Supplier supplier = supplierRepository.getOne(id);
	     
	            supplier.setSupplierName(newSupplier.getSupplierName());
	            supplier.setContactNo(newSupplier.getContactNo());
	            supplier.setAddress(newSupplier.getAddress());
	            supplier.setEmail(newSupplier.getEmail());
	            supplier.setLicense(newSupplier.getLicense());
	            
	            supplierRepository.save(supplier);
	}
	//---------------------Method for get all supplier list-------------------------------- 
	public List<Supplier> getSuppliers(){
		return supplierRepository.findAll();
	}
	//---------------------Method to search list of supplier by id------------------------
	public Optional<Supplier> getSupplierById(int id){
		return supplierRepository.findById(id);
	}
	//--------------------Method to search a list of supplier by supplier name-------------
	public List<Supplier> getSupplierByName(String sName){
		return supplierRepository.findBySupplierName(sName);
	}
} 
