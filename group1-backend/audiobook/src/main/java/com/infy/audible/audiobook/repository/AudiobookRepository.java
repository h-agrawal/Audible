package com.infy.audible.audiobook.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.infy.audible.audiobook.collection.Audiobook;

public interface AudiobookRepository extends MongoRepository<Audiobook, Long>{

}
