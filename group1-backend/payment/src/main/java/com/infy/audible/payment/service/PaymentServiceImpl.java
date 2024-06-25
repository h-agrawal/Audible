package com.infy.audible.payment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.audible.payment.collection.Payment;
import com.infy.audible.payment.collection.SequenceId;
import com.infy.audible.payment.dto.PaymentDTO;
import com.infy.audible.payment.dto.StringDTO;
import com.infy.audible.payment.exception.AudibleExceptionPayment;
import com.infy.audible.payment.repository.PaymentRepository;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private MongoOperations mongoOperations;

	private static final String HOSTING_SEQ_KEY = "hosting";

	@Override
	public Long getNextSequenceId(String key) throws AudibleExceptionPayment {
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);
		Query query = new Query(Criteria.where("_id").is(key));
		Update update = new Update();
		update.inc("seq", 1);
		SequenceId seqId = mongoOperations.findAndModify(query, update, options, SequenceId.class);
		if (seqId == null) {
			throw new AudibleExceptionPayment("Unable to get sequence id for key : " + key);
		}
		return seqId.getSeq();
	}

	@Override
	public StringDTO saveCard(PaymentDTO paymnetDTO) throws AudibleExceptionPayment {
		StringDTO stringDTO = new StringDTO();
		Payment payment = new Payment();
		payment.setCardNumber(paymnetDTO.getCardNumber());
		payment.setCustomerId(paymnetDTO.getCustomerId());
		payment.setCvv(paymnetDTO.getCvv());
		payment.setMonth(paymnetDTO.getMonth());
		payment.setName(paymnetDTO.getName());
		payment.setYear(paymnetDTO.getYear());
		List<Payment> oPayment = paymentRepository.findByCustomerIds(paymnetDTO.getCustomerId());
		if (oPayment.isEmpty()) {
			payment.setId(getNextSequenceId(HOSTING_SEQ_KEY));
			paymentRepository.save(payment);
			stringDTO.setMessage("Card added successfully");
			return stringDTO;
		}
		payment.setId(oPayment.get(0).getId());
		paymentRepository.save(payment);
		stringDTO.setMessage("Card upadted successfully");
		return stringDTO;
	}

	@Override
	public PaymentDTO cardRetrive(Long id) throws AudibleExceptionPayment {
		List<Payment> paymentList = paymentRepository.findByCustomerIds(id);
		if (paymentList.isEmpty())
			throw new AudibleExceptionPayment("SERVICE.NO_SAVED_CARD");
		Payment payment = paymentList.get(0);
		PaymentDTO paymentDTO = new PaymentDTO();
		paymentDTO.setCardNumber(payment.getCardNumber());
		paymentDTO.setCustomerId(id);
		paymentDTO.setCvv(payment.getCvv());
		paymentDTO.setMonth(payment.getMonth());
		paymentDTO.setName(payment.getName());
		paymentDTO.setYear(payment.getYear());
		return paymentDTO;

	}

}
