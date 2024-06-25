package com.infy.audible.cart.collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Cart {
	@Id
	private Long id;
	private Long audiobookId;
	private Long customerId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAudiobookId() {
		return audiobookId;
	}
	public void setAudiobookId(Long audiobookId) {
		this.audiobookId = audiobookId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	

}
