package com.ums.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ums.entity.UserDetails;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Integer> {

	@Query("select u.username, u.email from UserDetails u")
	public List<UserDetails> searchUser(String search);
	
	public List<UserDetails> findByUsername(String username);
	
}
