package com.cityfashionpos.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cityfashionpos.service.ImageStorageService;

@Service
public class ImageStorageServiceImpl implements ImageStorageService {
	@Value("${product.image.upload-dir}")
    private String uploadDir;

	@Override
	public String saveImage(MultipartFile file) throws IOException {
		if (file.isEmpty()) {
            throw new IllegalArgumentException("Image file is empty");
        }

        // Clean and generate unique filename
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        String fileExtension = getFileExtension(originalFilename);
        String uniqueFilename = System.currentTimeMillis() + "_" + originalFilename;

        // Create upload directory if it doesn't exist
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Save file
        Path filePath = uploadPath.resolve(uniqueFilename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Return relative or full path (adjust as needed)
        return "/images/products/" + uniqueFilename;
	}
	
    private String getFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        return (dotIndex >= 0) ? filename.substring(dotIndex) : "";
    }

}
