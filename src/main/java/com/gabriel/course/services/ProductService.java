package com.gabriel.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.course.entities.Product;
import com.gabriel.course.repositories.ProductRepository;
import com.gabriel.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> findAll() {
		return productRepository.findAll();

	}
	
	public List<Product> findByName(String name) {
		try {
			
		return productRepository.searchByNameIgnoreCase(name);
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(name);
		}
	}
	
	public List<Product> findMaxPrice(double price) {
		return productRepository.searchProductMaxPrice(price);
	}
	
	public Product findById(Long id) {
		Optional<Product> productOptional = productRepository.findById(id);
		return productOptional.get();
	}

	public Product save(Product product) {
		 return productRepository.save(product);
	}
	

}
