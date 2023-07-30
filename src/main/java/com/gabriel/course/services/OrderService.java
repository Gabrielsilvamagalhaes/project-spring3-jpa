package com.gabriel.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.course.entities.Order;
import com.gabriel.course.repositories.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;

	public List<Order> findAll() {
		return orderRepository.findAll();

	}
	
	public Order findById(Long id) {
		Optional<Order> orderOptional = orderRepository.findById(id);
		return orderOptional.get();
	}

	public Order save(Order order) {
		 return orderRepository.save(order);
	}
	

}
