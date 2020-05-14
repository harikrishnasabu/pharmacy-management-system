package com.example.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.OrderProductService;

@RestController
@RequestMapping("orderproduct")
public class OrderProductController {
	@Autowired
	OrderProductService orderProductService;
	@DeleteMapping(value = "/deleteOrderProduct")
	public void deleteOrderProduct(@RequestParam String id, @RequestParam int pId ) {
		orderProductService.deleteOrderProduct(id, pId);
	}

}
