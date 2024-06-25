package com.infy.audible.cart.service;

import java.util.List;

import com.infy.audible.cart.dto.CartDTO;
import com.infy.audible.cart.dto.StringDTO;
import com.infy.audible.cart.exception.AudibleExceptionCart;

public interface CartService {

	public StringDTO addItemToCart(CartDTO cartDTO) throws AudibleExceptionCart;

	public Long getNextSequenceId(String key) throws AudibleExceptionCart;

	public List<CartDTO> getItemFromoCart(Long customerId) throws AudibleExceptionCart;

	public StringDTO deleteItemFromCart(CartDTO cartDTO) throws AudibleExceptionCart;

	public StringDTO deleteCartForCustomer(Long id)throws AudibleExceptionCart;

}
