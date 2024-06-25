package com.infy.audible.payment.collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Payment {
	@Id
	private Long id;
	private Long customerIds;
	private String cardNumbers;
	private String names;
	private Integer months;
	private Integer years;
	private Integer cvvs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerIds;
	}

	public void setCustomerId(Long customerId) {
		this.customerIds = customerId;
	}

	public String getCardNumber() {
		return cardNumbers;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumbers = cardNumber;
	}

	public String getName() {
		return names;
	}

	public void setName(String name) {
		this.names = name;
	}

	public Integer getMonth() {
		return months;
	}

	public void setMonth(Integer month) {
		this.months = month;
	}

	public Integer getYear() {
		return years;
	}

	public void setYear(Integer year) {
		this.years = year;
	}

	public Integer getCvv() {
		return cvvs;
	}

	public void setCvv(Integer cvv) {
		this.cvvs = cvv;
	}

}
