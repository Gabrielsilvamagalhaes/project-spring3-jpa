package com.gabriel.course.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gabriel.course.entities.Product;

//@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query("SELECT p FROM Product p WHERE p.price <= :maxPrice")
	List<Product> searchProductMaxPrice(@Param("maxPrice") double price);
	
	@Query("SELECT p FROM Product p WHERE LOWER(p.name) = LOWER(:name)")
	List<Product> searchByNameIgnoreCase(@Param("name") String name);
}
