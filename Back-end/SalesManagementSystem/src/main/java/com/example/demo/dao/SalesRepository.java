package com.example.demo.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Order;

public interface SalesRepository extends JpaRepository<Order, String> {
	@Query(value="SELECT o FROM Order o where o.customerName=:name")
	public Optional<Order> findByCustName(String name);
	
	@Query(value="SELECT o FROM Order o where o.soDate BETWEEN :from AND :to")
	public List<Order> findByOrderDate(Date from, Date to);

}
