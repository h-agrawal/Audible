package com.infy.audible.dto;


public class AverageRatingDTO {
	private Long numberOfUsers;
	private Double averageRating;
	public Long getNumberOfUsers() {
		return numberOfUsers;
	}
	public void setNumberOfUsers(Long numberOfUsers) {
		this.numberOfUsers = numberOfUsers;
	}
	public Double getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}
	@Override
	public String toString() {
		return "AverageRatingDTO [users=" + numberOfUsers + ", averageRating=" + averageRating + "]";
	}


}
