package com.infy.audible.dto;

public class CartDTO {
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
	public CartDTO(Long id, Long audiobookId, Long customerId) {
		super();
		this.id = id;
		this.audiobookId = audiobookId;
		this.customerId = customerId;
	}
	

}
