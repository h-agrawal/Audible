package com.infy.audible.cart.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.infy.audible.cart.collection.Cart;
public interface CartRepository extends MongoRepository<Cart,Long>{

	Optional<Cart> findByAudiobookIdAndCustomerId(Long audiobookId, Long customerId);

	List<Cart> findByCustomerId(Long customerId);

}
