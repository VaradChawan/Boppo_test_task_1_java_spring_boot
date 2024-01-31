package com.ums.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ums.entity.UserDetails;
import com.ums.repository.UserRepository;
import com.ums.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
//	@PostMapping("/home")
//	public String home() {
//		return "test endpoint";
//	}
	
	@PostMapping("/registerNewUser")
	public ResponseEntity<String> registerNewUser(
			@RequestBody UserDetails userdetails 
			) {
		
		UserDetails newUser= userService.registerNewUser(userdetails); 
		
		if(newUser == null) {
			return new ResponseEntity<String>("Username already present",HttpStatus.CREATED);
		}
		
		else {
		return new ResponseEntity<String>("Successfully register", HttpStatus.CREATED);
		}
	}
	
	@PostMapping("/loginUser")
	public ResponseEntity<String> loginUser (
			@RequestBody UserDetails userdetails
			){
		
		List<UserDetails> userDetails= userService.loginUser(userdetails.getUsername(), userdetails.getPassword());
		//System.out.println(userDetails.password);
		
		
		
		if(userDetails == null) {
			return new ResponseEntity<String>("Check username and password", HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<String>("Login Successfully",HttpStatus.ACCEPTED); 
		}

	}
	
	
	
	@PutMapping("/passwordReset/{id}/{newPassword}")
	public ResponseEntity<String> passwordReset(
			@RequestBody UserDetails user,
			@PathVariable String newPassword,
			@PathVariable("id") Integer id
			
			) {
		//System.out.println(email);
		
		//System.out.println(user.getPassword());
	//	System.out.println(newPassword);
		 
		
		Optional<UserDetails> users= this.userService.findById(id);
		//System.out.println(users);
		UserDetails userPasswordUpdate= user;
		
		if(users == null) {
			return new ResponseEntity<String>("User not found",HttpStatus.NOT_FOUND);
		}else {
			userPasswordUpdate.setId(id);
			userPasswordUpdate.setPassword(newPassword);
			userRepository.save(userPasswordUpdate);
			return new ResponseEntity<String>("Password changed successfully", HttpStatus.ACCEPTED);	
		}
		
	}
	
	@GetMapping("/getAllUser")
	public ResponseEntity<List<UserDetails>> getAllUser(){
		
		List<UserDetails> user= userService.getAllUser();
		
		return new ResponseEntity<List<UserDetails>> (user, HttpStatus.ACCEPTED);
	}
	
	
	
}
