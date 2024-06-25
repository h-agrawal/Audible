package com.infy.audible.collection;

import java.util.List;

import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer")
public class Customer {
	
	@Id
	private Long id;
	private List<Long> audiobookId;
	
	private String name;
	private String email;
	private String username;
	private Password password;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Password getPassword() {
		return password;
	}
	public void setPassword(Password password) {
		this.password = password;
	}
	public List<Long> getAudiobookId() {
		return audiobookId;
	}
	public void setAudiobookId(List<Long> audiobookId) {
		this.audiobookId = audiobookId;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", username=" + username + ", password="
				+ password + "]";
	}
}
