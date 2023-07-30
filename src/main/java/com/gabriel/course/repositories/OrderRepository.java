package com.gabriel.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.course.entities.Order;

public interface OrderRepository  extends JpaRepository<Order, Long> { 

}
