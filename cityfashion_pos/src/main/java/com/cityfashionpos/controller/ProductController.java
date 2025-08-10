package com.cityfashionpos.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cityfashionpos.dto.ProductRequestDTO;
import com.cityfashionpos.dto.ProductResponseDTO;
import com.cityfashionpos.entity.ProductEntity;
import com.cityfashionpos.service.ImageStorageService;
import com.cityfashionpos.service.ProductService;
import com.cityfashionpos.service.ProductServiceNew;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")

public class ProductController {
	@Autowired
	private ProductService productService;

	@Autowired
	private ProductServiceNew productServiceNew;
	
	@Autowired
	private ImageStorageService imageStorageService;

	@PostMapping("/saveProduct")
	public ResponseEntity<ProductEntity> saveProduct(@RequestBody ProductEntity product) {
		ProductEntity saved = productService.saveProduct(product);
		return ResponseEntity.ok(saved);
	}

	@GetMapping("/getAllProducts")
	public ResponseEntity<List<ProductEntity>> getAllProducts() {
		return ResponseEntity.ok(productService.getAllProducts());
	}

	@PutMapping("/updateProduct/{id}")
	public ResponseEntity<ProductEntity> updateProduct(@PathVariable Long id, @RequestBody ProductEntity product) {
		product.setId(id);
		return ResponseEntity.ok(productService.saveProduct(product));
	}

	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/getProductByBarcode/{barcode}")
	public ResponseEntity<ProductEntity> getProductByBarcode(@PathVariable String barcode) {
		return productService.getProductByBarcode(barcode).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/bulkImport")
	public ResponseEntity<?> importProducts(@RequestParam("file") MultipartFile file) {
		try {
			productService.importFromExcel(file);
			return ResponseEntity.ok("Products imported successfully.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to import: " + e.getMessage());
		}
	}

	@PostMapping(value="/saveProductNew", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Map<String, Object>> saveProductNew(
			@RequestPart("product") ProductRequestDTO requestDTO,
			@RequestPart("imageFile") MultipartFile imageFile) {
		Map<String, Object> response = new HashMap<>();

		try {
			// Save image to disk or cloud
	        String imageUrl = imageStorageService.saveImage(imageFile);
	        
	        // Attach image URL to DTO
	        requestDTO.setImageUrl(imageUrl);

			ProductResponseDTO savedProduct = productServiceNew.saveProduct(requestDTO);

			response.put("success", true);
			response.put("message", "Product saved successfully");
			response.put("product", savedProduct);

			return ResponseEntity.ok(response);

		} catch (IllegalArgumentException e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			return ResponseEntity.badRequest().body(response);

		} catch (Exception e) {
			response.put("success", false);
			response.put("message", "Error saving product: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	/**
	 * Get product by ID
	 */
	@GetMapping("/getProductById/{id}")
	public ResponseEntity<Map<String, Object>> getProductById(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		try {
			ProductResponseDTO product = productServiceNew.getProductById(id);

			response.put("success", true);
			response.put("product", product);

			return ResponseEntity.ok(response);

		} catch (IllegalArgumentException e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			response.put("success", false);
			response.put("message", "Error retrieving product: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	/**
	 * Get product by code
	 */
	@GetMapping("/getProductByCode/{code}")
	public ResponseEntity<Map<String, Object>> getProductByCode(@PathVariable String code) {
		Map<String, Object> response = new HashMap<>();

		try {
			ProductResponseDTO product = productServiceNew.getProductByCode(code);

			response.put("success", true);
			response.put("product", product);

			return ResponseEntity.ok(response);

		} catch (IllegalArgumentException e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			response.put("success", false);
			response.put("message", "Error retrieving product: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

    /**
     * Get all products
     */
    @GetMapping("/getAllProductsNew")
    public ResponseEntity<Map<String, Object>> getAllProductsNew() {
        Map<String, Object> response = new HashMap<>();
        
        try {
            List<ProductResponseDTO> products = productServiceNew.getAllProducts();
            
            response.put("success", true);
            response.put("products", products);
            response.put("count", products.size());
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error retrieving products: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * Search products by name
     */
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchProductsByName(@RequestParam String name) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            List<ProductResponseDTO> products = productServiceNew.searchProductsByName(name);
            
            response.put("success", true);
            response.put("products", products);
            response.put("count", products.size());
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error searching products: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * Get products by category
     */
    @GetMapping("/getProductsByCategory/{category}")
    public ResponseEntity<Map<String, Object>> getProductsByCategory(@PathVariable String category) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            List<ProductResponseDTO> products = productServiceNew.getProductsByCategory(category);
            
            response.put("success", true);
            response.put("products", products);
            response.put("count", products.size());
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error retrieving products by category: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * Get products with low stock
     */
    @GetMapping("/getProductsWithLowStock")
    public ResponseEntity<Map<String, Object>> getProductsWithLowStock() {
        Map<String, Object> response = new HashMap<>();
        
        try {
            List<ProductResponseDTO> products = productServiceNew.getProductsWithLowStock();
            
            response.put("success", true);
            response.put("products", products);
            response.put("count", products.size());
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error retrieving low stock products: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * Update product
     */
    @PutMapping("/updateProductNew/{id}")
    public ResponseEntity<Map<String, Object>> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO requestDTO) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            ProductResponseDTO updatedProduct = productServiceNew.updateProduct(id, requestDTO);
            
            response.put("success", true);
            response.put("message", "Product updated successfully");
            response.put("product", updatedProduct);
            
            return ResponseEntity.ok(response);
            
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error updating product: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * Delete product
     */
    @DeleteMapping("/deleteProductNew/{id}")
    public ResponseEntity<Map<String, Object>> deleteProductNew(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            productService.deleteProduct(id);
            
            response.put("success", true);
            response.put("message", "Product deleted successfully");
            
            return ResponseEntity.ok(response);
            
        } catch (IllegalArgumentException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error deleting product: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * Generate unique product code
     */
    @GetMapping("/generateProductCode")
    public ResponseEntity<Map<String, Object>> generateProductCode() {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String code = productServiceNew.generateProductCode();
            
            response.put("success", true);
            response.put("code", code);
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error generating product code: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String uploadDir = "uploads/";
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir + fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());

            return ResponseEntity.ok("Image uploaded successfully: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed");
        }
    }
}
