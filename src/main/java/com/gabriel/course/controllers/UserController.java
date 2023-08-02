package com.gabriel.course.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabriel.course.entities.User;
import com.gabriel.course.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> searchAll() {
		return ResponseEntity.ok().body(userService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> searchById(@PathVariable Long id) {
		return ResponseEntity.ok().body(userService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user) {
		User temporaryUser = userService.save(user);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(temporaryUser.getId()).toUri();
		
		return ResponseEntity.created(uri).body(temporaryUser);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		userService.delete(id);
		
		return ResponseEntity.ok("Cliente excluido com sucesso!");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
		return ResponseEntity.ok().body(userService.update(id, user));
	}
	
}
