package com.infy.audible.audiobook.collection;

public class UserReviews {
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

}
