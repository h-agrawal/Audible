package com.infy.audible.dto;
import jakarta.validation.constraints.NotNull;

public class LoginDTO {
	@NotNull(message = "{username.absent}")
	private String username;
	
	@NotNull(message = "{password.absent}")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
