package com.cityfashionpos.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageService {
	String saveImage(MultipartFile file) throws IOException;
}
