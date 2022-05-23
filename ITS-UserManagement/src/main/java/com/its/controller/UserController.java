package com.its.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.its.dto.User;
import com.its.service.UserService;

@RestController
@RequestMapping("/its-login")
@CrossOrigin(origins="*")
public class UserController {
	
	@Autowired
	UserService userService;

	//1
	@PostMapping(value="/user/authenticate", consumes={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> authenticate(@RequestBody User user) {
		return new ResponseEntity<String>(userService.authenticate(user),HttpStatus.OK);
	}
		
	/*
	 * //2
	 * 
	 * @DeleteMapping(value="/user/logout") public ResponseEntity<Boolean>
	 * logout(@RequestHeader("Authorization") String authToken) { return new
	 * ResponseEntity<Boolean>(userService.logout(authToken),HttpStatus.OK); }
	 */
		
	//3
	@PostMapping(value="/user",consumes={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<User> addUser(@RequestBody User user) {
		return new ResponseEntity<User>(userService.addUser(user),HttpStatus.OK);
	}
		
	//4
	@GetMapping(value="/user",produces={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<User> getUser(@RequestHeader("Authorization") String authToken) {
		return new ResponseEntity<User>(userService.getUser(authToken),HttpStatus.OK);
	}
		
	//5
	@GetMapping(value="/token/validate",produces={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Boolean> validateJWT(@RequestHeader("Authorization")String authToken) {
		return new ResponseEntity<Boolean>(userService.validateJWT(authToken), HttpStatus.OK);
	}
	
	//5.1
	@GetMapping(value="/token/admin/validate",produces={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Boolean> validateAdminJWT(@RequestHeader("Authorization")String authToken) {
		return new ResponseEntity<Boolean>(userService.validateAdminJWT(authToken), HttpStatus.OK);
	}
	
	//5.2
	@GetMapping(value="/token/tech/validate",produces={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Boolean> validateTechJWT(@RequestHeader("Authorization")String authToken) {
		return new ResponseEntity<Boolean>(userService.validateTechJWT(authToken), HttpStatus.OK);
	}
	
	//5.3
	@GetMapping(value="/token/hr/validate",produces={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Boolean> validateHRJWT(@RequestHeader("Authorization")String authToken) {
		return new ResponseEntity<Boolean>(userService.validateHRJWT(authToken), HttpStatus.OK);
	}
}
