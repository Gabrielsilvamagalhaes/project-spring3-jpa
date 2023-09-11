package com.gabriel.course.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.course.entities.Order;
import com.gabriel.course.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@GetMapping
	public ResponseEntity<List<Order>> searchAll() {
		var lst = orderService.findAll();
		
		for(Order o : lst) {
			o.add(linkTo(methodOn(OrderController.class).searchById(o.getId())).withSelfRel());
		}
		
		return ResponseEntity.ok().body(lst);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> searchById(@PathVariable Long id) {
		var order = orderService.findById(id);
		order.add(linkTo(methodOn(OrderController.class).searchAll()).withRel("Orders List"));
		
		return ResponseEntity.ok().body(order);
	}
	
	@PostMapping
	public ResponseEntity<Order> save(@RequestBody Order Order) {
		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(Order));
	}

}
