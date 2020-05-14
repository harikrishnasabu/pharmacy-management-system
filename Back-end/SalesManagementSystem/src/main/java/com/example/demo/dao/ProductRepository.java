package com.example.demo.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product>{
	@Query(value="SELECT p FROM Product p where p.pExpiryDate BETWEEN :from AND :to")
    public List<Product> findByExpiryDate(@Param("from") Date from, @Param("to") Date to);
	
	@Query(value="SELECT p FROM Product p where p.sId=:sid")
	public List<Product> findBySupplier(@Param("sid") int sid);
	
	@Query(value = "SELECT p from Product p where p.pName LIKE %:pname%")
	public List<Product> findByProductName(@Param("pname") String pname);
}
