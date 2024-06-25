package com.infy.audible.payment.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.infy.audible.payment.collection.Payment;

public interface PaymentRepository extends MongoRepository<Payment, Long>{

	public List<Payment> findByCustomerIds(Long id);

}
