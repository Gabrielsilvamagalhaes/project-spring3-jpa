package com.gabriel.course.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gabriel.course.entities.Product;

//@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query("SELECT p FROM Product p WHERE p.price <= :maxPrice")
	List<Product> searchProductMaxPrice(@Param("maxPrice") double price);
	
	@Query("SELECT p FROM Product p WHERE p.price >= :minPrice")
	List<Product> searchProductMinPrice(@Param("minPrice") double price);
	
	@Query(value = "SELECT DISTINCT p.* " +
		       "FROM app_product p " +
		       "INNER JOIN app_product_category pc ON p.id = pc.product_Id " +
		       "INNER JOIN app_category c ON pc.category_Id = c.id " +
		       "WHERE p.price >= :minPrice " +
		       "AND p.price <= :maxPrice " +
		       "AND c.name IN :categoryNames", nativeQuery = true)
	List<Product> searchProductByPriceAndCategory(@Param("minPrice") BigDecimal minPrice, 
			@Param("maxPrice") BigDecimal maxPrice, @Param("categoryNames") List<String> categoriesNames);
	
	@Query("SELECT p FROM Product p WHERE p.price >= :minPrice AND p.price <= :maxPrice")
	List<Product> searchByPriceBetween(@Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice);
	
	@Query("SELECT p FROM Product p WHERE LOWER(p.name) = LOWER(:name)")
	List<Product> searchByNameIgnoreCase(@Param("name") String name);
}
