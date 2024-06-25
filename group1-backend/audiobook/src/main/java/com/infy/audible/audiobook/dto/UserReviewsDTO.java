package com.infy.audible.audiobook.dto;

import com.infy.audible.audiobook.collection.UserReviews;

public class UserReviewsDTO {
	private String customerReview;
	private String customerName;
	public String getCustomerReview() {
		return customerReview;
	}
	public void setCustomerReview(String customerReview) {
		this.customerReview = customerReview;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	@Override
	public String toString() {
		return "UserReviewsDTO [customerReview=" + customerReview + ", customerName=" + customerName + "]";
	}
	public void convertUserReviewsToDTO(UserReviews userReview) {
		this.setCustomerName(userReview.getCustomerName());
		this.setCustomerReview(userReview.getCustomerReview());
	}
	public UserReviewsDTO(String customerReview, String customerName) {
		super();
		this.customerReview = customerReview;
		this.customerName = customerName;
	}

}
