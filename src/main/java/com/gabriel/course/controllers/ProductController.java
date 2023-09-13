package com.gabriel.course.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.course.entities.Product;
import com.gabriel.course.services.ProductService;


@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<Product>> searchAll() {
		var lst = productService.findAll();
		
		for(Product p : lst) {
		p.add(linkTo(methodOn(ProductController.class).searchById(p.getId())).withSelfRel());
		}
		
		return ResponseEntity.ok().body(lst);
	}
	
	@GetMapping("/search-by-between-price")
	public ResponseEntity<List<Product>> searchByPriceBetween(@RequestParam("minPrice") BigDecimal minPrice, @RequestParam("maxPrice") BigDecimal maxPrice) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.findByPriceBetween(minPrice, maxPrice));
	
	}
	
	@GetMapping("/search-by-name")
	public ResponseEntity<List<Product>> searchByName(@RequestParam String name) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.findByName(name));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> searchById(@PathVariable Long id) {
		var product = productService.findById(id);
		product.add(linkTo(methodOn(ProductController.class).searchAll()).withRel("Products List"));
		
		return ResponseEntity.ok().body(product);
	}
	
	@GetMapping("/search-max-price")
	public ResponseEntity<List<Product>> searchMaxPrice(@RequestParam("maxPrice") double price) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.findMaxPrice(price));
	}
	
	@GetMapping("/search-min-price")
	public ResponseEntity<List<Product>> searchMinxPrice(@RequestParam("minPrice") double price) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.findMinPrice(price));
	}
	
	@GetMapping("/search-by-price-and-category")
	public ResponseEntity<List<Product>> searchByPriceAndCategory(@RequestParam("minPrice") BigDecimal minPrice, 
			@RequestParam("maxPrice") BigDecimal maxPrice, @RequestParam("categoryName") List<String> categories) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.findByPriceAndCategory(minPrice, maxPrice, categories));
	}
	
	@PostMapping
	public ResponseEntity<Product> save(@RequestBody Product product) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
	}
	
}
