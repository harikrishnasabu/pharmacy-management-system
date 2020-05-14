package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

	@Query(value = "SELECT s from Supplier s where s.supplierName LIKE %:sName%")
	public List<Supplier> findBySupplierName(@Param("sName") String sName);
}
