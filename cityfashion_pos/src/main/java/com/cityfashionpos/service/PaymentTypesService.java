package com.cityfashionpos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cityfashionpos.entity.PaymentTypesEntity;

@Service
public interface PaymentTypesService {

	public List<PaymentTypesEntity> getAllPaymentModes();

}
