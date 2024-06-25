package com.infy.audible.audiobook.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.infy.audible.audiobook.collection.Audiobook;

public class AudiobookDTO {
	
	private Long id;
	private String title;
	

	private String author;
	private String genre;
	private AverageRatingDTO averageRatingDTO;
	private String briefDescription;
	private String narrator;
	private LocalDate publishedDate;
	private LocalDate releaseDate;
	private List<UserReviewsDTO> userReviews;
	private String publisher;
	private String audioURL;
	private String thumnailURL;
	private String sampleAudioURL;
	private Double price;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getAudioURL() {
		return audioURL;
	}
	public void setAudioURL(String audioURL) {
		this.audioURL = audioURL;
	}
	public String getThumnailURL() {
		return thumnailURL;
	}
	public void setThumnailURL(String thumnailURL) {
		this.thumnailURL = thumnailURL;
	}
	public String getSampleAudioURL() {
		return sampleAudioURL;
	}
	public void setSampleAudioURL(String sampleAudioURL) {
		this.sampleAudioURL = sampleAudioURL;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public AverageRatingDTO getAverageRatingDTO() {
		return averageRatingDTO;
	}
	public void setAverageRatingDTO(AverageRatingDTO averageRatingDTO) {
		this.averageRatingDTO = averageRatingDTO;
	}
	public String getBriefDescription() {
		return briefDescription;
	}
	public void setBriefDescription(String briefDescription) {
		this.briefDescription = briefDescription;
	}
	public String getNarrator() {
		return narrator;
	}
	public void setNarrator(String narrator) {
		this.narrator = narrator;
	}
	public LocalDate getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(LocalDate publishedDate) {
		this.publishedDate = publishedDate;
	}
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
	public List<UserReviewsDTO> getUserReviews() {
		return userReviews;
	}
	public void setUserReviews(List<UserReviewsDTO> userReviews) {
		this.userReviews = userReviews;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	@Override
	public String toString() {
		return "AudiobookDTO [id=" + id + ", author=" + author + ", genre=" + genre + ", averageRatingDTO="
				+ averageRatingDTO + ", briefDescription=" + briefDescription + ", narrator=" + narrator
				+ ", publishedDate=" + publishedDate + ", releaseDate=" + releaseDate + ", userReviews=" + userReviews
				+ ", publisher=" + publisher + "]";

	}
	
	public AudiobookDTO convertAudiobookToDTO(Audiobook audiobook) {
		AudiobookDTO audiobookDTO=new AudiobookDTO();
		audiobookDTO.setAuthor(audiobook.getAuthor());
		
		AverageRatingDTO averageRatingDTO = new AverageRatingDTO();
		averageRatingDTO.convetAverageRatingtoDTO(audiobook.getAverageRating());
		audiobookDTO.setAverageRatingDTO(averageRatingDTO);
		audiobookDTO.setPrice(audiobook.getPrice());
		audiobookDTO.setTitle(audiobook.getTitle());
		audiobookDTO.setBriefDescription(audiobook.getBriefDescription());
		audiobookDTO.setGenre(audiobook.getGenre());
		audiobookDTO.setId(audiobook.getId());
		audiobookDTO.setNarrator(audiobook.getNarrator());
		audiobookDTO.setPublishedDate(audiobook.getPublishedDate());
		audiobookDTO.setPublisher(audiobook.getPublisher());
		audiobookDTO.setReleaseDate(audiobook.getReleaseDate());
		audiobookDTO.setAudioURL(audiobook.getAudioURL());
		audiobookDTO.setThumnailURL(audiobook.getThumnailURL());
		audiobookDTO.setSampleAudioURL(audiobook.getSampleAudioURL());
		audiobookDTO.setUserReviews(audiobook.getUserReviews().stream().map(m->new UserReviewsDTO(m.getCustomerReview(),m.getCustomerName())).collect(Collectors.toList()));
		 return audiobookDTO;
	}

}
