package com.cityfashionpos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cityfashionpos.entity.ProductEntity;
import com.cityfashionpos.service.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")

public class ProductController {
	@Autowired
	private ProductService productService;
	
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
	    return productService.getProductByBarcode(barcode)
	            .map(ResponseEntity::ok)
	            .orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/bulkImport")
	public ResponseEntity<?> importProducts(@RequestParam("file") MultipartFile file) {
	    try {
	        productService.importFromExcel(file);
	        return ResponseEntity.ok("Products imported successfully.");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Failed to import: " + e.getMessage());
	    }
	}

}
