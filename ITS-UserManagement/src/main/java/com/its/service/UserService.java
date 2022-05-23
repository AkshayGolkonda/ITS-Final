package com.its.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;

import com.its.dto.User;

public interface UserService {
	
	public String authenticate(User user);
	//public boolean logout(String authToken);
	public User addUser(User user);
	public User getUser(String authtoken);
	public boolean validateJWT(String authToken);
	
	
	//5.1
	public boolean validateAdminJWT(String authToken);
	//5.2
	public boolean validateTechJWT(String authToken);
	//5.3
	public boolean validateHRJWT(String authToken);
	
}
