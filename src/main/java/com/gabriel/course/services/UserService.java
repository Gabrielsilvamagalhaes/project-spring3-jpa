package com.gabriel.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.gabriel.course.entities.User;
import com.gabriel.course.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();

	}
	
	public User findById(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		return userOptional.get();
	}

	public User save(User user) {
		 return userRepository.save(user);
	}
	
	public void delete(Long id) {
		User user = userRepository.findById(id)
		.orElseThrow(()-> new ResourceAccessException("Cliente não encontrado com o ID: " + id));
		
		userRepository.delete(user);
	}
	

}
