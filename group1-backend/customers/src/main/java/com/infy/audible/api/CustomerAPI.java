package com.infy.audible.api;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.reactive.function.client.WebClient;

import com.infy.audible.dto.AudiobookDTO;
import com.infy.audible.dto.CartDTO;
import com.infy.audible.dto.CustomerDTO;
import com.infy.audible.dto.LoginDTO;
import com.infy.audible.dto.PasswordDTO;
import com.infy.audible.dto.StringDTO;
import com.infy.audible.exception.AudibleExceptionCustomer;
import com.infy.audible.service.CustomerService;

import jakarta.validation.Valid;
@CrossOrigin("*")
@RestController
@Validated
@RequestMapping("/customer")
public class CustomerAPI {

	@Autowired
	private CustomerService customerService;
	
	
	WebClient webClient=WebClient.create();
	
	private static final Log LOGGER=LogFactory.getLog(CustomerAPI.class);

	@PostMapping("/login")
	public ResponseEntity<CustomerDTO> authenticateCustomer(@Valid @RequestBody LoginDTO loginDTO) throws AudibleExceptionCustomer {
		LOGGER.info("CUSTOMER TRYING TO LOGIN, VALIDATING CREDENTIALS. CUSTOMER USERNAME: " + loginDTO.getUsername());
			CustomerDTO customerDTOFromDB = customerService.authenticateCustomer(loginDTO.getUsername(),
					loginDTO.getPassword());
			LOGGER.info("CUSTOMER LOGIN SUCCESS, CUSTOMER USERNAME : " + customerDTOFromDB.getUsername());
			return new ResponseEntity<>(customerDTOFromDB, HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<CustomerDTO> authenticateCustomer(@Valid @RequestBody CustomerDTO customerDTO)
			throws AudibleExceptionCustomer {
		
		LOGGER.info("CUSTOMER TRYING TO REGISTER. CUSTOMER USERNAME: " + customerDTO.getUsername());
		CustomerDTO registeredWithUsername = customerService.registerNewCustomer(customerDTO);
		return new ResponseEntity<>(registeredWithUsername, HttpStatus.OK);
	}
	
	@PostMapping("/updatepassword/{id}")
	public ResponseEntity<StringDTO> updatePassword(@RequestBody PasswordDTO passwordDTO, @PathVariable Long id)
			throws AudibleExceptionCustomer {
//		TODO
		LOGGER.info("CUSTOMER TRYING TO UPDATE PASSWORD: " + id);
		StringDTO passwordUpdate = customerService.updatePassword(passwordDTO,id);
		return new ResponseEntity<>(passwordUpdate, HttpStatus.OK);
	}
	@GetMapping("/audiobook/{id}")
	public ResponseEntity<List<AudiobookDTO>> getCustomerAudiobook(@PathVariable Long id)
			throws AudibleExceptionCustomer {
//		
		LOGGER.info("CUSTOMER TRYING FETCH AUDIOBOOK: " + id);
		AudiobookDTO[] arr =  webClient.put().uri("http://localhost:9500/audiobook/library").bodyValue(customerService.getCustomer(id).getAudiobookId()).retrieve().bodyToMono(AudiobookDTO[].class).block();
		List<AudiobookDTO> list = Arrays.asList(arr);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@PostMapping("/addPurchase")
	public ResponseEntity<StringDTO> addAudiobook(@RequestBody List<CartDTO> cartDTOs) throws AudibleExceptionCustomer{
		LOGGER.info("CUSTOMER TRYING add AUDIOBOOK for: "+cartDTOs.get(0).getCustomerId());
		return new ResponseEntity<>(customerService.addPurchase(cartDTOs), HttpStatus.OK);
		
	}
	
	@GetMapping("/profile/{id}")
	public ResponseEntity<CustomerDTO> getProfile(@PathVariable Long id)
			throws AudibleExceptionCustomer {
//		
		LOGGER.info("CUSTOMER TRYING FETCH AUDIOBOOK: " + id);
		
		return new ResponseEntity<>(customerService.getCustomer(id), HttpStatus.OK);
	}
	

}
