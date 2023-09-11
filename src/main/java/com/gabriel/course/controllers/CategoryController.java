package com.gabriel.course.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.course.entities.Category;
import com.gabriel.course.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<Category>> searchAll() {
		var lst = categoryService.findAll();
		
		for(Category c : lst) {
			c.add(linkTo(methodOn(CategoryController.class).searchById(c.getId())).withSelfRel());
		}
		
		return ResponseEntity.ok().body(lst);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> searchById(@PathVariable Long id) {
		var category = categoryService.findById(id);
		category.add(linkTo(methodOn(CategoryController.class).searchAll()).withRel("Categories List"));

		return ResponseEntity.ok().body(category);
	}
	
	@PostMapping
	public ResponseEntity<Category> save(@RequestBody Category category) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(categoryService.save(category));
	}
	
}
