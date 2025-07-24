package com.cityfashionpos.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConfigUtils {
	@Value("${app.secretkey}")
	private String secretKey;

	public String getSecretKey() {
		return secretKey;
	}
	
	

}
