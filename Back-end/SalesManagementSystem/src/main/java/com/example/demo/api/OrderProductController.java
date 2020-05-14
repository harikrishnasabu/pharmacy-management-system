package com.example.demo.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.OrderProductService;

@CrossOrigin
@RestController
@RequestMapping("orderproducts")
public class OrderProductController {
		@Autowired
		OrderProductService orderProductService;
		@DeleteMapping(value="/deleteorderproduct")
		public void deleteOrderProducts(@RequestParam String id,@RequestParam int pid) {
			orderProductService.deleteOrderProducts(id,pid);
		}
}
