package com.cityfashionpos.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityfashionpos.entity.CustomerEntity;
import com.cityfashionpos.repository.CustomerRepository;
import com.cityfashionpos.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public CustomerEntity saveCustomer(CustomerEntity customer) {
		return customerRepository.save(customer);
	}

	@Override
	public List<CustomerEntity> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Optional<CustomerEntity> getCustomerById(Long id) {
		return customerRepository.findById(id);
	}

	@Override
	public List<CustomerEntity> searchByName(String name) {
		return customerRepository.findByNameContainingIgnoreCase(name);
	}

	@Override
	public CustomerEntity updateCustomer(Long id, CustomerEntity updatedCustomer) {
		CustomerEntity existing = customerRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

		existing.setName(updatedCustomer.getName());
		existing.setPhone(updatedCustomer.getPhone());
		existing.setEmail(updatedCustomer.getEmail());
		existing.setAddress(updatedCustomer.getAddress());

		return customerRepository.save(existing);
	}

	@Override
	public void deleteCustomer(Long id) {
		if (!customerRepository.existsById(id)) {
	        throw new RuntimeException("Customer not found with id: " + id);
	    }
	    customerRepository.deleteById(id);

	}

}
