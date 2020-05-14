package com.example.demo.dao;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {
		
	@Transactional
	@Modifying
	@Query(value="DELETE FROM OrderProduct op where op.orderId=:id and op.productId=:pid")
    public void deleteOrderProducts(String id,int pid);
	
	@Query(value="SELECT op FROM OrderProduct op where op.orderId=:id")
    public List<OrderProduct> findOrderProductsByOrder(String id);
	
	@Query(value="SELECT op FROM OrderProduct op where op.orderId=:id and op.productId=:pid")
    public OrderProduct findOrderProducts(String id,int pid);
}
