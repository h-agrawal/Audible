package com.infy.audible.payment.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.audible.payment.dto.PaymentDTO;
import com.infy.audible.payment.dto.StringDTO;
import com.infy.audible.payment.exception.AudibleExceptionPayment;
import com.infy.audible.payment.service.PaymentService;

//@CrossOrigin("*")
@RequestMapping("/payment")
@RestController
public class PaymentAPI {

	@Autowired
	private PaymentService paymentService;

	private static final Log LOGGER = LogFactory.getLog(PaymentAPI.class);

	@PostMapping("/saveCard")
	public ResponseEntity<StringDTO> addCard(@RequestBody PaymentDTO paymnetDTO) throws AudibleExceptionPayment {
		LOGGER.info("CUSTOMER TRYING TO SAVE CARD : ");
		return new ResponseEntity<>(paymentService.saveCard(paymnetDTO), HttpStatus.OK);

	}

	@GetMapping("/card/{id}")
	public ResponseEntity<PaymentDTO> getCard(@PathVariable Long id) throws AudibleExceptionPayment {
		LOGGER.info("CUSTOMER TRYING TO get CARD FOR: " + id);
		return new ResponseEntity<>(paymentService.cardRetrive(id), HttpStatus.OK);
	}
}
