package com.gabriel.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.gabriel.course.entities.User;
import com.gabriel.course.repositories.UserRepository;
import com.gabriel.course.services.exceptions.DataBaseException;
import com.gabriel.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();

	}
	
	public User findById(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		return userOptional.orElseThrow(()-> new ResourceNotFoundException(id));
	}

	public User save(User user) {
		 return userRepository.save(user);
	}
	
	public void delete(Long id) {
		try {
		User user = userRepository.findById(id)
		.orElseThrow(()-> new ResourceNotFoundException(id));
		userRepository.delete(user);
		
		}catch(DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
			
		}
		
	}
	
	public User update(Long id, User updatedUser) {
		try {
		User targetUser = userRepository.getReferenceById(id);
		updateData(targetUser, updatedUser);
		return userRepository.save(targetUser);
		
		}catch(EntityNotFoundException e) {
			throw new  ResourceNotFoundException(id);
		}
	}

	private void updateData(User targetUser, User updatedUser) {
		targetUser.setName(updatedUser.getName());
		targetUser.setEmail(updatedUser.getEmail());
		targetUser.setPhone(updatedUser.getPhone());
	}
	
		
	

}
