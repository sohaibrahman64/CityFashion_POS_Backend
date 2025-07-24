package com.cityfashionpos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cityfashionpos.entity.CustomerEntity;
import com.cityfashionpos.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/saveCustomer")
	public ResponseEntity<CustomerEntity> saveCustomer(@RequestBody CustomerEntity customer) {
		return ResponseEntity.ok(customerService.saveCustomer(customer));
	}

	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<CustomerEntity>> getAllCustomers() {
		return ResponseEntity.ok(customerService.getAllCustomers());
	}

	@GetMapping("/getCustomer/{id}")
	public ResponseEntity<CustomerEntity> getCustomer(@PathVariable Long id) {
		return customerService.getCustomerById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/searchByName")
	public ResponseEntity<List<CustomerEntity>> searchByName(@RequestParam String keyword) {
		return ResponseEntity.ok(customerService.searchByName(keyword));
	}
	
	@PutMapping("/updateCustomer/{id}")
	public ResponseEntity<CustomerEntity> updateCustomer(@PathVariable Long id, @RequestBody CustomerEntity updatedCustomer) {
	    return ResponseEntity.ok(customerService.updateCustomer(id, updatedCustomer));
	}
	
	@DeleteMapping("/deleteCustomer/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
	    customerService.deleteCustomer(id);
	    return ResponseEntity.noContent().build();
	}


}
