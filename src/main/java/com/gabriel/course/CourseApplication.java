package com.gabriel.course;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gabriel.course.entities.Order;
import com.gabriel.course.entities.User;
import com.gabriel.course.entities.enums.OrderStatus;
import com.gabriel.course.repositories.OrderRepository;
import com.gabriel.course.repositories.UserRepository;


@SpringBootApplication
public class CourseApplication implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	public static void main(String[] args) {
		SpringApplication.run(CourseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Gabriel", "gabriel@gmail.com", "(71)-984606034", "59710826");
		User u2 = new User(null, "Luis", "Luis@gmail.com", "(71)-9993334414", "12345678");
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1); 
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2); 
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.CANCELED, u1);
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));		
	}


}
