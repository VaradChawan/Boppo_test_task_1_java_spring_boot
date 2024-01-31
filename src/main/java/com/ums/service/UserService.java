package com.ums.service;

import java.util.List;
import java.util.Optional;

import com.ums.entity.UserDetails;

public interface UserService {

	
	public UserDetails registerNewUser(UserDetails userDetails);
	
	public List<UserDetails> loginUser(String username, String password);

	public void userPasswordReset(String password, String newPassword);	
	
	public Optional<UserDetails> findById(Integer id);
	
	public List<UserDetails> getAllUser();
}
