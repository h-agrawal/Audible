package com.infy.audible.service;

import java.util.List;

import com.infy.audible.dto.CartDTO;
import com.infy.audible.dto.CustomerDTO;
import com.infy.audible.dto.PasswordDTO;
import com.infy.audible.dto.StringDTO;
import com.infy.audible.exception.AudibleExceptionCustomer;



public interface CustomerService {

	public CustomerDTO registerNewCustomer(CustomerDTO customerDTO) throws AudibleExceptionCustomer;
	public Long getNextSequenceId(String key) throws AudibleExceptionCustomer;
	public CustomerDTO authenticateCustomer(String username, String password) throws AudibleExceptionCustomer;
	public CustomerDTO getCustomer(Long id) throws AudibleExceptionCustomer;
	public StringDTO addPurchase(List<CartDTO> cartDTOs) throws AudibleExceptionCustomer;
	public StringDTO updatePassword(PasswordDTO paaawordDTO, Long id) throws AudibleExceptionCustomer;

}
