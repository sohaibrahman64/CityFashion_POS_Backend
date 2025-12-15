package com.cityfashionpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cityfashionpos.entity.PaymentTypesEntity;
import com.cityfashionpos.repository.PaymentTypesRepository;
import com.cityfashionpos.service.PaymentTypesService;

public class PaymentTypesImpl implements PaymentTypesService {

	@Autowired
	PaymentTypesRepository paymentModeRepository;

	@Override
	public List<PaymentTypesEntity> getAllPaymentModes() {
		return paymentModeRepository.findAll();
	}

}
