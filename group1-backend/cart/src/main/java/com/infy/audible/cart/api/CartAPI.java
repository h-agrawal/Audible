package com.infy.audible.cart.api;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.audible.cart.dto.CartDTO;
import com.infy.audible.cart.dto.StringDTO;
import com.infy.audible.cart.exception.AudibleExceptionCart;
import com.infy.audible.cart.service.CartService;

@CrossOrigin("*")
@RestController
@RequestMapping("/cart")
public class CartAPI {
	@Autowired
	private Environment environment;
	
	@Autowired
	private CartService cartService;
	
	private static final Log LOGGER=LogFactory.getLog(CartAPI.class);
	
	@PostMapping("/add")
	public ResponseEntity<StringDTO> addToCart(@RequestBody CartDTO cartDTO) throws AudibleExceptionCart{
		LOGGER.info("CUSTOMER TRYING TO add element to cart");
		return ResponseEntity.ok(cartService.addItemToCart(cartDTO));
	}
	@PostMapping("/delete")
	public ResponseEntity<StringDTO> deleteFromCart(@RequestBody CartDTO cartDTO) throws AudibleExceptionCart{
		LOGGER.info("CUSTOMER TRYING TO add delete particular element for particular customer");
		return ResponseEntity.ok(cartService.deleteItemFromCart(cartDTO));
	}
	@GetMapping("/Items/{id}")
	public ResponseEntity<List<CartDTO>> get(@PathVariable Long id) throws AudibleExceptionCart{
		LOGGER.info("CUSTOMER TRYING TO fetch his/her cart");
		return ResponseEntity.ok(cartService.getItemFromoCart(id));
	}
	@DeleteMapping("/Items/{id}")
	public ResponseEntity<StringDTO> delete(@PathVariable Long id) throws AudibleExceptionCart{
		LOGGER.info("CUSTOMER purchasing all items");
		return ResponseEntity.ok(cartService.deleteCartForCustomer(id));
	}
}
