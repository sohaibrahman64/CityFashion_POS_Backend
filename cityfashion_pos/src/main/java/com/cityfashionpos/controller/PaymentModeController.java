package com.cityfashionpos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cityfashionpos.entity.PaymentModeEntity;
import com.cityfashionpos.repository.PaymentModeRepository;

@RestController
@RequestMapping("/api/payment-modes")
@CrossOrigin(origins = "*")
public class PaymentModeController {

	@Autowired
	private PaymentModeRepository repository;

	@GetMapping("/getAllPaymentModes")
	public ResponseEntity<List<PaymentModeEntity>> getAllModes() {
		return ResponseEntity.ok(repository.findAll());
	}
}
