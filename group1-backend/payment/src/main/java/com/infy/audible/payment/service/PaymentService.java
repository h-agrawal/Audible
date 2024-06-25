package com.infy.audible.payment.service;

import com.infy.audible.payment.dto.PaymentDTO;
import com.infy.audible.payment.dto.StringDTO;
import com.infy.audible.payment.exception.AudibleExceptionPayment;

public interface PaymentService {

	public StringDTO saveCard(PaymentDTO paymnetDTO) throws AudibleExceptionPayment;

	public Long getNextSequenceId(String key) throws AudibleExceptionPayment;

	public PaymentDTO cardRetrive(Long id) throws AudibleExceptionPayment;

}
