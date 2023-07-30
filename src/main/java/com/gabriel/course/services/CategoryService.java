package com.gabriel.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.course.entities.Category;
import com.gabriel.course.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> findAll() {
		return categoryRepository.findAll();

	}
	
	public Category findById(Long id) {
		Optional<Category> categoryOptional = categoryRepository.findById(id);
		return categoryOptional.get();
	}

	public Category save(Category category) {
		 return categoryRepository.save(category);
	}
	

}
