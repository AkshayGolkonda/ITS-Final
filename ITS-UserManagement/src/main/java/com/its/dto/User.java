package com.its.dto;

public class User {
	
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String role;
	public User(String firstName, String lastName, String userName, String password, String role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
	
	public User() {
		super();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName + ", password="
				+ password + ", role=" + role + "]";
	}

	@Override
	public boolean equals(Object obj) {
	User advertiseDto = (User)obj;
	if(this.userName.equals(advertiseDto.getUserName()))
	return true;
	return false;
	}
	
	
	
	
}
