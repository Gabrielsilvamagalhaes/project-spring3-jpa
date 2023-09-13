package com.gabriel.course.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gabriel.course.entities.User;

//@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT e FROM User e WHERE LOWER(e.name) = LOWER(:name)")
	List<User> searchByNameIgnoreCase(@Param("name") String name);

}
