package com.gabriel.course.controllers;

import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.course.dtos.UserDTO;
import com.gabriel.course.entities.User;
import com.gabriel.course.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> searchAll() {
		List<User> lst = userService.findAll();
		List<UserDTO> lstDTO = lst.stream()
				.map(x -> new UserDTO(x.getName(), x.getEmail(), x.getPassword()))
				.collect(Collectors.toList());
		
		return ResponseEntity.status(HttpStatus.OK).body(lstDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> searchById(@PathVariable Long id) {
		return ResponseEntity.ok().body(userService.findById(id));
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
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
		return ResponseEntity.ok().body(userService.update(id, user));
	}
	
}
