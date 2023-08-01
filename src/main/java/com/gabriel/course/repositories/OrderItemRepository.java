package com.gabriel.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.course.entities.OrderItem;

//@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
