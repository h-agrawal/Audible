package com.infy.audible.audiobook.service;

import java.util.List;

import com.infy.audible.audiobook.dto.AudiobookDTO;
import com.infy.audible.audiobook.exception.AudibleAudiobookException;

public interface AudiobookService {
	
	public Long getNextSequenceId(String key) throws AudibleAudiobookException;
	public List<AudiobookDTO> fetchallaudiosample() throws AudibleAudiobookException;
	public List<AudiobookDTO> fetchById(List<Long> audiobookId) throws AudibleAudiobookException;
	public String updateRating(Long id, Double value)throws AudibleAudiobookException;

}
