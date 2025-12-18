package com.cityfashionpos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cityfashionpos.entity.PaymentTypesEntity;
import com.cityfashionpos.repository.PaymentTypesRepository;

@RestController
@RequestMapping("/api/payment-types")
@CrossOrigin(origins = "*")
public class PaymentTypesController {

	@Autowired
	private PaymentTypesRepository repository;

	@GetMapping("/getAll")
	public ResponseEntity<List<PaymentTypesEntity>> getAllModes() {
		// return ResponseEntity.ok(repository.findAll());
		return ResponseEntity.ok(repository.getAllPaymentTypes());
	}
}
