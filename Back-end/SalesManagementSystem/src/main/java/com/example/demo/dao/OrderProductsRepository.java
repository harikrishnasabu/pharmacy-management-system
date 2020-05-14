package com.example.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.OrderProducts;

public interface OrderProductsRepository extends JpaRepository<OrderProducts,Integer> {
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM OrderProducts op where op.orderId=:id and op.productId=:pid")
    public void deleteOrderProducts(String id,int pid);
	
	@Query(value="SELECT op FROM OrderProducts op where op.orderId=:id")
    public List<OrderProducts> findOrderProductsByOrder(String id);
	
	@Query(value="SELECT op FROM OrderProducts op where op.orderId=:id and op.productId=:pid")
    public OrderProducts findOrderProducts(String id,int pid);
}
