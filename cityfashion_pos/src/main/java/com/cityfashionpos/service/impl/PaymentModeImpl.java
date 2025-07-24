package com.cityfashionpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cityfashionpos.entity.PaymentModeEntity;
import com.cityfashionpos.repository.PaymentModeRepository;
import com.cityfashionpos.service.PaymentModeService;

public class PaymentModeImpl implements PaymentModeService {
	
	@Autowired
	PaymentModeRepository paymentModeRepository;

	@Override
	public List<PaymentModeEntity> getAllPaymentModes() {
		return paymentModeRepository.findAll();
	}

}
