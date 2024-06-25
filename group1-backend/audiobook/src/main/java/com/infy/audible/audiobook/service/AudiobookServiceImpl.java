package com.infy.audible.audiobook.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.audible.audiobook.collection.Audiobook;
import com.infy.audible.audiobook.collection.AverageRating;
import com.infy.audible.audiobook.collection.SequenceId;
import com.infy.audible.audiobook.dto.AudiobookDTO;
import com.infy.audible.audiobook.exception.AudibleAudiobookException;
import com.infy.audible.audiobook.repository.AudiobookRepository;

@Service
@Transactional
public class AudiobookServiceImpl implements AudiobookService{
	
	@Autowired
	private MongoOperations mongoOperations;
	
	@Autowired
	private AudiobookRepository audiobookRepository;
	
	private static final String HOSTING_SEQ_KEY = "hosting";

	@Override
	public Long getNextSequenceId(String key) throws AudibleAudiobookException {
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);
		Query query = new Query(Criteria.where("_id").is(key));
		Update update = new Update();
		update.inc("seq", 1);
		SequenceId seqId = mongoOperations.findAndModify(query, update, options, SequenceId.class);
		if (seqId == null) {
			throw new AudibleAudiobookException("Unable to get sequence id for key : " + key);
		}
		return seqId.getSeq();
	}
	
	public List<AudiobookDTO> fetchallaudiosample() throws AudibleAudiobookException{
		List<Audiobook> audiobooks = audiobookRepository.findAll();
		AudiobookDTO audiobookDTO =new AudiobookDTO();
		List<AudiobookDTO> audiobookDTOs=
		audiobooks.stream().map(m->audiobookDTO.convertAudiobookToDTO(m)).collect(Collectors.toList());
		System.out.println(audiobookDTOs);
		return audiobookDTOs;
	}

	@Override
	public List<AudiobookDTO> fetchById(List<Long> audiobookId)throws AudibleAudiobookException {
		AudiobookDTO audiobookDTO =new AudiobookDTO();
		List<AudiobookDTO> audiobookDTOs=
		audiobookId.stream().map(m->audiobookDTO.convertAudiobookToDTO(audiobookRepository.findById(m).orElse(new Audiobook()))).collect(Collectors.toList());
		return audiobookDTOs;
	}

	@Override
	public String updateRating(Long id, Double value) throws AudibleAudiobookException {
		Audiobook audiobook= audiobookRepository.findById(id).orElseThrow(()-> new AudibleAudiobookException("SERVICE.NO_SUCH_AUDIOBOOK"));
		AverageRating averageRating = new AverageRating();
		averageRating.setAverageRating((audiobook.getAverageRating().getAverageRating()*audiobook.getAverageRating().getNumberOfUsers()+value)/(audiobook.getAverageRating().getNumberOfUsers()+1));
		averageRating.setNumberOfUsers(audiobook.getAverageRating().getNumberOfUsers()+1);
		audiobook.setAverageRating(averageRating);
		audiobookRepository.save(audiobook);
		return "Rating update successfully";
	}

}
