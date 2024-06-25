package com.infy.audible.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CustomerDTO {
	
	private Long id;
	private List<Long> audiobookId;
	
	
	@NotNull(message = "{name.absent}")
	@Pattern(regexp = "[A-Z][a-z]*( [A-Z][a-z]*)*", message = "{name.invalid}")
	private String name;
	
	@NotNull(message = "{email.absent}")
	@Pattern(regexp = "[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.(com|in|org)", message = "{email.invalid}")
	private String email;
	
	@NotNull(message = "{username.absent}")
	@Pattern(regexp = "[a-z0-9@#$&_]+",message = "{username.invalid}")
	private String username;
	
	@Valid
	private PasswordDTO password;
	
	public List<Long>  getAudiobookId() {
		return audiobookId;
	}



	public void setAudiobookId(List<Long>  audiobookId) {
		this.audiobookId = audiobookId;
	}
	
	@Override
	public String toString() {
		return "CustomerDTO [id=" + id + ", name=" + name + ", email=" + email + ", username=" + username
				+ ", password=" + password + "]";
	}
	
	
	
	public CustomerDTO(Long id,
			@NotNull(message = "{name.absent}") @Pattern(regexp = "[A-Z][a-z]*( [A-Z][a-z]*)*", message = "{name.invalid}") String name,
			@NotNull(message = "{email.absent}") @Pattern(regexp = "[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.(com|in|org)", message = "{email.invalid}") String email,
			@Pattern(regexp = "[a-z0-9@#$&_]+", message = "{username.invalid}") String username,
			@Valid PasswordDTO password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	
	public PasswordDTO getPassword() {
		return password;
	}
	public void setPassword(PasswordDTO password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	

}
