package com.deloitte.todo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
	

	@Query("SELECT u FROM user u WHERE username LIKE :username")
	public User findByUser(@Param("username") String userName);

}
