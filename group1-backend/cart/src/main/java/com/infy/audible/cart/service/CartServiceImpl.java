package com.infy.audible.cart.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.audible.cart.collection.Cart;
import com.infy.audible.cart.collection.SequenceId;
import com.infy.audible.cart.dto.CartDTO;
import com.infy.audible.cart.dto.StringDTO;
import com.infy.audible.cart.exception.AudibleExceptionCart;
import com.infy.audible.cart.repository.CartRepository;

@Service
@Transactional
public class CartServiceImpl implements CartService{
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private MongoOperations mongoOperations;
	
	private static final String HOSTING_SEQ_KEY = "hosting";
	
	@Override
	public Long getNextSequenceId(String key) throws AudibleExceptionCart {
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);
		Query query = new Query(Criteria.where("_id").is(key));
		Update update = new Update();
		update.inc("seq", 1);
		SequenceId seqId = mongoOperations.findAndModify(query, update, options, SequenceId.class);
		if (seqId == null) {
			throw new AudibleExceptionCart("Unable to get sequence id for key : " + key);
		}
		return seqId.getSeq();
	}

	@Override
	public StringDTO addItemToCart(CartDTO cartDTO) throws AudibleExceptionCart {
		StringDTO stringDTO=new StringDTO();
		stringDTO.setMessage("Item already present");
		if(cartRepository.findByAudiobookIdAndCustomerId(cartDTO.getAudiobookId(),cartDTO.getCustomerId()).isEmpty()) {
			Cart cart =new Cart();
			cart.setId(getNextSequenceId(HOSTING_SEQ_KEY));
			cart.setAudiobookId(cartDTO.getAudiobookId());
			cart.setCustomerId(cartDTO.getCustomerId());
			cartRepository.save(cart);
			
			stringDTO.setMessage("Item added to cart");
		}
		return stringDTO;
	}
	@Override
	public List<CartDTO> getItemFromoCart(Long customerId) throws AudibleExceptionCart {
		List<Cart> carts=cartRepository.findByCustomerId(customerId);
		List<CartDTO> cartDTOs;
		if(!carts.isEmpty()) {
			cartDTOs=carts.stream().map(m->new CartDTO(m.getId(), m.getAudiobookId(), m.getCustomerId())).collect(Collectors.toList());
			return cartDTOs;
		}
		
		throw new AudibleExceptionCart("SERVICE.CUSTOMER_HAVE_NO_ITEMS");
	}
	
	@Override
	public StringDTO deleteItemFromCart(CartDTO cartDTO) throws AudibleExceptionCart {
		Cart cart=cartRepository.findByAudiobookIdAndCustomerId(
				cartDTO.getAudiobookId(),cartDTO.getCustomerId())
				.orElseThrow(()->new AudibleExceptionCart("SERVICE.No_such_Item_present"));
		cartRepository.delete(cart);
		StringDTO stringDTO=new StringDTO();
		stringDTO.setMessage("Item deleted Successfully");
		return stringDTO;
	}

	@Override
	public StringDTO deleteCartForCustomer(Long id) throws AudibleExceptionCart {
		List<Cart> carts=cartRepository.findByCustomerId(id);
		StringDTO stringDTO= new StringDTO();

		if(!carts.isEmpty()) {
			for (Cart item : carts) {
				cartRepository.delete(item);
			}
			return stringDTO;
		}
		
		throw new AudibleExceptionCart("SERVICE.CUSTOMER_HAVE_NO_ITEMS");
	}

}
