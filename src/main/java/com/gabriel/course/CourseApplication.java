package com.gabriel.course;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gabriel.course.entities.Category;
import com.gabriel.course.entities.Order;
import com.gabriel.course.entities.OrderItem;
import com.gabriel.course.entities.Payment;
import com.gabriel.course.entities.Product;
import com.gabriel.course.entities.User;
import com.gabriel.course.entities.enums.OrderStatus;
import com.gabriel.course.repositories.CategoryRepository;
import com.gabriel.course.repositories.OrderItemRepository;
import com.gabriel.course.repositories.OrderRepository;
import com.gabriel.course.repositories.ProductRepository;
import com.gabriel.course.repositories.UserRepository;


@SpringBootApplication
public class CourseApplication implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	

	public static void main(String[] args) {
		SpringApplication.run(CourseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
	}


}
