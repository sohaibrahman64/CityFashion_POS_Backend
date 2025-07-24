package com.cityfashionpos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cityfashionpos.entity.PaymentModeEntity;

@Service
public interface PaymentModeService {
	
	public List<PaymentModeEntity> getAllPaymentModes();

}
