package com.ums.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ums.entity.UserDetails;
import com.ums.repository.UserRepository;
import com.ums.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails registerNewUser(UserDetails userDetails) {
		// TODO Auto-generated method stub
	
//		String username= userDetails.getUsername();
//		String userEmail= userDetails.getEmail();
		List<UserDetails> user= userRepository.findByUsername(userDetails.getUsername());
		
		if(user.isEmpty()) {
			UserDetails newUser= userRepository.save(userDetails);
			return newUser;
		}
		else {
			return null;
		}
	
	}

	@Override
	public List<UserDetails> loginUser(String username, String password) {
		// TODO Auto-generated method stub
		
		List<UserDetails> user= userRepository.findByUsername(username);
//		System.out.println(user);
		
		
		if(user.isEmpty()) {
			return null;
		}
		else {
			
			for(UserDetails u: user) {
				System.out.println(u.getPassword());
				System.out.println(password);
				if( password.equals(u.getPassword()))
				{System.out.println("User found");
					return user;
				}else {
					System.out.println("User not found");
					return null;
				}
				
			}
			
		}

		return null;
		
	}

	@Override
	public void userPasswordReset(String password, String newPassword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<UserDetails> findById(Integer id) {
		// TODO Auto-generated method stub
		Optional<UserDetails> findUser= this.userRepository.findById(id);
				
		if(findUser.isEmpty()) {
			System.out.println("User not found");
			return null;
		}else {
			return findUser;
		}
		
		
	}

	@Override
	public List<UserDetails> getAllUser() {
		// TODO Auto-generated method stub
		
	List<UserDetails> allUsers=userRepository.findAll();
	
		return allUsers;
	}

	


}

