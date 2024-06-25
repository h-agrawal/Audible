package com.infy.audible.payment;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;

import com.infy.audible.payment.collection.Payment;
import com.infy.audible.payment.collection.SequenceId;
import com.infy.audible.payment.dto.PaymentDTO;
import com.infy.audible.payment.exception.AudibleExceptionPayment;
import com.infy.audible.payment.repository.PaymentRepository;
import com.infy.audible.payment.service.PaymentServiceImpl;

@SpringBootTest
class PaymentApplicationTests {
	
	@Mock
	private PaymentRepository paymentRepository;
	
	@Mock
	private MongoOperations mongoOperations;
	
	@InjectMocks
	private PaymentServiceImpl paymentService = new PaymentServiceImpl();

	@Test
	void saveCardNewTest() throws AudibleExceptionPayment {
		PaymentDTO paymentDTO = new PaymentDTO();
		paymentDTO.setCustomerId(1l);		
		List<Payment> payments=new ArrayList<>();
		SequenceId sequenceId = new SequenceId();
		sequenceId.setSeq(1l);
		
		Mockito.when(paymentRepository.findByCustomerIds(Mockito.anyLong())).thenReturn(payments);
		Mockito.when(mongoOperations.findAndModify(any(), any(), any(), eq(SequenceId.class))).thenReturn(sequenceId);
		
		Assertions.assertEquals("Card added successfully", paymentService.saveCard(paymentDTO).getMessage());
	}

	@Test
	void updateCardNewTest() throws AudibleExceptionPayment {
		PaymentDTO paymentDTO = new PaymentDTO();
		paymentDTO.setCustomerId(1l);
		Payment payment =new Payment();
		payment.setId(1l);
		
		List<Payment> payments=new ArrayList<>();
		payments.add(payment);
		
		Mockito.when(paymentRepository.findByCustomerIds(Mockito.anyLong())).thenReturn(payments);
		
		Assertions.assertEquals("Card upadted successfully", paymentService.saveCard(paymentDTO).getMessage());
	}
	
	@Test
	void retriveCardExceptionTest() throws AudibleExceptionPayment {
		Mockito.when(paymentRepository.findByCustomerIds(Mockito.anyLong())).thenReturn(new ArrayList<>());
		
		Exception e = Assertions.assertThrows(AudibleExceptionPayment.class,()-> paymentService.cardRetrive(1l));
		Assertions.assertEquals("SERVICE.NO_SAVED_CARD", e.getMessage());
	}
	
	@Test
	void retriveCardSuccessTest() throws AudibleExceptionPayment {
		List<Payment> payments =new ArrayList<>();
		Payment payment = new Payment();
		payments.add(payment);
		Mockito.when(paymentRepository.findByCustomerIds(Mockito.anyLong())).thenReturn(payments);
		
		Assertions.assertEquals(1l, paymentService.cardRetrive(1l).getCustomerId());
	}
	
}
