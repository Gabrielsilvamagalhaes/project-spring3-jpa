package com.gabriel.course.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.course.dtos.UserDTO;
import com.gabriel.course.entities.User;
import com.gabriel.course.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> searchAll() {
		var <User> lst = userService.findAll();
		
		for(User user : lst) {
			user.add(linkTo(methodOn(UserController.class).searchById(user.getId())).withSelfRel());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(lst);
	}
	
	@GetMapping("/search-by-name")
	public ResponseEntity<List<User>> searchByName(@RequestParam String name) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.findByName(name));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> searchById(@PathVariable Long id) {
		var user = userService.findById(id);
		user.add(linkTo(methodOn(UserController.class).searchAll()).withRel("Users List"));
		
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> save(@RequestBody @Valid User user) {
		var  userDTO = new UserDTO(user.getName(), user.getEmail(), user.getPhone());
		userService.save(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		userService.delete(id);
		return ResponseEntity.ok("Cliente excluido com sucesso!");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody @Valid User user) {
		var updatedUser = userService.update(id, user);
		return ResponseEntity.ok().body(new UserDTO(updatedUser.getName(), updatedUser.getEmail(), updatedUser.getPhone()));
	}
	
}
