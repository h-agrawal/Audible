package com.infy.audible.audiobook.api;

import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.audible.audiobook.dto.AudiobookDTO;
import com.infy.audible.audiobook.exception.AudibleAudiobookException;
import com.infy.audible.audiobook.service.AudiobookService;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@CrossOrigin("*")
@Validated
@RestController
@RequestMapping("/audiobook")
public class CustomerAudiobookAPI {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private AudiobookService audiobookService;
	
	private static final Log LOGGER=LogFactory.getLog(CustomerAudiobookAPI.class);
	
	@GetMapping("/sampleaudio")
	public ResponseEntity<List<AudiobookDTO>> fetchallsamples() throws AudibleAudiobookException{
		LOGGER.info("CUSTOMER TRYING TO get all audiobooks");
		return ResponseEntity.ok(audiobookService.fetchallaudiosample());
	}
	
	@PutMapping("/library")
	public ResponseEntity<List<AudiobookDTO>> fetchById(@RequestBody List<Long> audiobookId) throws AudibleAudiobookException{
		LOGGER.info("CUSTOMER TRYING TO fetch its library");
		return ResponseEntity.ok(audiobookService.fetchById(audiobookId));
	}
	
	@PostMapping("/ratings/{id}/{value}")
	public ResponseEntity<String> updateRating(@PathVariable Long id, @PathVariable @Min(value = 0,message = "{min.value}") @Max(value = 5,message = "{max.value}") Double value) throws AudibleAudiobookException{
		LOGGER.info("CUSTOMER TRYING TO update rating");
		return ResponseEntity.ok(audiobookService.updateRating(id,value));
	}

}
