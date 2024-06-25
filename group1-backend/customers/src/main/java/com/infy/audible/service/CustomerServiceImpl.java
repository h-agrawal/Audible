package com.infy.audible.service;

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

import com.infy.audible.collection.Customer;
import com.infy.audible.collection.Password;
import com.infy.audible.collection.SequenceId;
import com.infy.audible.dto.CartDTO;
import com.infy.audible.dto.CustomerDTO;
import com.infy.audible.dto.PasswordDTO;
import com.infy.audible.dto.StringDTO;
import com.infy.audible.exception.AudibleExceptionCustomer;
import com.infy.audible.repository.CustomerRepository;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private MongoOperations mongoOperations;
	
	private static final String HOSTING_SEQ_KEY = "hosting";
	
	@Override
	public Long getNextSequenceId(String key) throws AudibleExceptionCustomer{
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);
		Query query = new Query(Criteria.where("_id").is(key));
		Update update = new Update();
		update.inc("seq", 1);
		SequenceId seqId = mongoOperations.findAndModify(query, update, options, SequenceId.class);
		if (seqId == null) {
			throw new AudibleExceptionCustomer("Unable to get sequence id for key : " + key);
		}
		return seqId.getSeq();
	}

	@Override
	public CustomerDTO registerNewCustomer(CustomerDTO customerDTO) throws AudibleExceptionCustomer{
		if(customerRepository.findByUsername(customerDTO.getUsername()).isEmpty()) {
			Customer customer = new Customer();
			customer.setId(getNextSequenceId(HOSTING_SEQ_KEY));
			customer.setEmail(customerDTO.getEmail());
			customer.setName(customerDTO.getName());
			Password password=new Password();
			password.setCurrentPassword(customerDTO.getPassword().getCurrentPassword());
			customer.setPassword(password);
			customer.setUsername(customerDTO.getUsername());
			customerRepository.save(customer);
			customerDTO.setId(customer.getId());
			return customerDTO;
		}
		
		throw new AudibleExceptionCustomer("SERVICE.username_already_exist");
	}

	@Override
	public CustomerDTO authenticateCustomer(String username, String password) throws AudibleExceptionCustomer {
		System.out.println(customerRepository.findAll());
		Customer customer = customerRepository.findByUsername(username).orElseThrow(()->new AudibleExceptionCustomer("SERVICE.username_not_found"));
		System.out.println(customer.getPassword()+""+password);
		if (customer.getPassword().getCurrentPassword().equals(password)) {
			CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getName(), customer.getEmail(), customer.getUsername(), null);
			customerDTO.setAudiobookId(customer.getAudiobookId());
			return customerDTO;
		}
		throw new AudibleExceptionCustomer("SERVICE.invalid_password");
	}
	
	@Override
	public CustomerDTO getCustomer(Long id) throws AudibleExceptionCustomer{
		Customer customer= customerRepository.findById(id).orElseThrow(()-> new AudibleExceptionCustomer("SERVICE.no_such_user"));
		CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getName(), customer.getEmail(), customer.getUsername(), null);
		customerDTO.setAudiobookId(customer.getAudiobookId());
		return customerDTO;
	}

	@Override
	public StringDTO addPurchase(List<CartDTO> cartDTOs) throws AudibleExceptionCustomer {
		Customer customer= new Customer();
		StringDTO stringDTO = new StringDTO();
		if (!cartDTOs.isEmpty()) {
			customer= customerRepository.findById(cartDTOs.get(0).getCustomerId()).orElseThrow(()->new AudibleExceptionCustomer("SERVICE.no_such_user"));
			if(customer.getAudiobookId()==null || customer.getAudiobookId().isEmpty()) {
				customer.setAudiobookId(cartDTOs.stream().map(m->m.getAudiobookId()).collect(Collectors.toList()));
				
				
			}
			else {
				List<Long> audiobookIds=customer.getAudiobookId();
			for (CartDTO cartDTO : cartDTOs) {
				audiobookIds.add(cartDTO.getAudiobookId());
			}
			customer.setAudiobookId(audiobookIds);
			}
			customerRepository.save(customer);
			stringDTO.setMessage("Audiobook added to Account");
			return stringDTO;
		}
		
		throw new AudibleExceptionCustomer("SERVICE.no_item_present");
	}

	@Override
	public StringDTO updatePassword(PasswordDTO passwordDTO, Long id) throws AudibleExceptionCustomer {
		Customer customer = customerRepository.findById(id).orElseThrow(()->new AudibleExceptionCustomer("SERVICE.no_such_user"));
		if (customer.getPassword().getCurrentPassword().equals(passwordDTO.getPreviousPassword())) {
			if(customer.getPassword().getOldPassword()!=null && customer.getPassword().getOldPassword().equals(passwordDTO.getCurrentPassword())) {
				throw new AudibleExceptionCustomer("SERVICE.ONE_OF_OLD_PASSWORD");
			}
			if(customer.getPassword().getPreviousPassword()!=null && customer.getPassword().getPreviousPassword().equals(passwordDTO.getCurrentPassword())) {
				throw new AudibleExceptionCustomer("SERVICE.ONE_OF_OLD_PASSWORD");
			}
			PasswordDTO passwordDTO2 = new PasswordDTO();
			if(customer.getPassword().getPreviousPassword()!=null) {
				passwordDTO2.setOldPassword(customer.getPassword().getPreviousPassword());
			}
			passwordDTO2.setPreviousPassword(customer.getPassword().getCurrentPassword());
			passwordDTO2.setCurrentPassword(passwordDTO.getCurrentPassword());
			Password password = new Password();
			password.setCurrentPassword(passwordDTO2.getCurrentPassword());
			password.setOldPassword(passwordDTO2.getOldPassword());
			password.setPreviousPassword(passwordDTO2.getPreviousPassword());
			customer.setPassword(password);
			customerRepository.save(customer);
			StringDTO stringDTO = new StringDTO();
			stringDTO.setMessage("Password Updated Successfully");
			return stringDTO;
		}
		throw new AudibleExceptionCustomer("SERVICE.invalid_password");
	}

	
	

}
