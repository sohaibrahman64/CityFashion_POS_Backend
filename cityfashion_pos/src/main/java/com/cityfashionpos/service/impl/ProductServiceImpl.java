package com.cityfashionpos.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cityfashionpos.entity.InventoryEntity;
import com.cityfashionpos.entity.InventoryMovementTypeEntity;
import com.cityfashionpos.entity.InventoryMovementsEntity;
import com.cityfashionpos.entity.ProductCategoryEntity;
import com.cityfashionpos.entity.ProductEntity;
import com.cityfashionpos.entity.SupplierEntity;
import com.cityfashionpos.repository.InventoryMovementsRepository;
import com.cityfashionpos.repository.InventoryMovementsTypeRepository;
import com.cityfashionpos.repository.InventoryRepository;
import com.cityfashionpos.repository.ProductCategoryRepository;
import com.cityfashionpos.repository.ProductRepository;
import com.cityfashionpos.repository.SupplierRepository;
import com.cityfashionpos.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductCategoryRepository categoryRepository;
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	@Autowired
	private InventoryMovementsRepository inventoryMovementsRepository;
	
	@Autowired
	private InventoryMovementsTypeRepository inventoryMovementsTypeRepository;

	@Override
	public ProductEntity saveProduct(ProductEntity product) {
		if (product.getBarcode() == null || product.getBarcode().isEmpty()) {
			throw new IllegalArgumentException("Barcode is required.");
		}

		// ✅ If creating a new product
		if (product.getId() == null) {
			if (productRepository.existsByBarcode(product.getBarcode())) {
				throw new IllegalArgumentException("Barcode already exists.");
			}
		} else {
			// ✅ If updating — barcode should not belong to another product
			if (productRepository.existsByBarcodeAndIdNot(product.getBarcode(), product.getId())) {
				throw new IllegalArgumentException("Barcode already exists.");
			}
		}

		return productRepository.save(product);
	}

	@Override
	public List<ProductEntity> getAllProducts() {
		return productRepository.findAllWithCategory();
	}

	@Override
	public void deleteProduct(Long id) {
		if (!productRepository.existsById(id)) {
			throw new RuntimeException("Product not found");
		}
		productRepository.deleteById(id);
	}

	@Override
	public Optional<ProductEntity> getProductByBarcode(String barcode) {
		return productRepository.findByBarcode(barcode);
	}

	@Override
	@Transactional
	public void importFromExcel(MultipartFile file) throws IOException {
		try (InputStream inputStream = file.getInputStream()) {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());
	        Sheet sheet = workbook.getSheetAt(0);

	        for (Row row : sheet) {
	            if (row.getRowNum() == 0) continue; // skip header row
	            
	            String billNumber = String.valueOf(row.getCell(0).getNumericCellValue());
	            LocalDate stockDate = getCellDateValue(row.getCell(1));
	            String supplierName = getCellValue(row.getCell(2));
	            String productName = getCellValue(row.getCell(3));
	            String categoryName = getCellValue(row.getCell(4));
	            String size = getCellValue(row.getCell(5));
	            int stockQuantity = (int)row.getCell(6).getNumericCellValue();
	            double buyPrice = row.getCell(7).getNumericCellValue();
	            double mrp = row.getCell(8).getNumericCellValue();
	            double tax = row.getCell(9).getNumericCellValue();
	            
	            //String name = getCellValue(row.getCell(0));
	            //int quantity = (int) row.getCell(2).getNumericCellValue();
	            //double mrp = row.getCell(4).getNumericCellValue();

	            // 🔹 Check or create Category
	            ProductCategoryEntity category = categoryRepository.findByName(categoryName)
	                    .orElseGet(() -> {
	                        ProductCategoryEntity newCategory = new ProductCategoryEntity();
	                        newCategory.setName(categoryName);
	                        return categoryRepository.save(newCategory);
	                    });
	            
	            SupplierEntity supplierEntity = supplierRepository.findByName(supplierName)
	            		.orElseGet(() -> {
	            			SupplierEntity newSupplier = new SupplierEntity();
	            			newSupplier.setName(supplierName);
	            			return supplierRepository.save(newSupplier);
	            		});

	            // 🔹 Check existing Product by name + size + category
	            Optional<ProductEntity> optionalProduct = productRepository
	                    .findByNameAndSizeAndCategoryId(productName, size, category.getId());

	            if (optionalProduct.isPresent()) {
	                // 🔹 Existing product: update stock
	                ProductEntity existingProduct = optionalProduct.get();
	                existingProduct.setMrp(mrp); // update MRP if needed

	                productRepository.save(existingProduct);

	                InventoryEntity inventory = inventoryRepository.findByProductId(existingProduct.getId())
	                        .orElseThrow(() -> new IllegalStateException("Inventory missing for product ID: " + existingProduct.getId()));
	                inventory.setQuantity(inventory.getQuantity() + stockQuantity);
	                inventoryRepository.save(inventory);

	                // Log stock movement
	                createInventoryMovement(existingProduct, stockQuantity, "PURCHASE");
	            } else {
	                // 🔹 New product: create Product + generate barcode
	                ProductEntity newProduct = new ProductEntity();
	                newProduct.setName(productName);
	                newProduct.setSize(size);
	                newProduct.setCategory(category);
	                newProduct.setMrp(mrp);
	                newProduct.setBarcode(generateBarcode()); // Generate unique barcode
	                newProduct.setTax_percent(tax);
	                productRepository.save(newProduct);

	                // Create inventory
	                InventoryEntity newInventory = new InventoryEntity();
	                newInventory.setProduct(newProduct);
	                newInventory.setQuantity(stockQuantity);
	                inventoryRepository.save(newInventory);

	                // Log stock movement
	                createInventoryMovement(newProduct, stockQuantity, "PURCHASE");
	            }
	        }
	        workbook.close();
		}
	}
	
	private LocalDate getCellDateValue(Cell cell) {
		LocalDate cellDateValue;

		if (cell != null) {
		    if (cell.getCellType() == CellType.NUMERIC) {
		        if (DateUtil.isCellDateFormatted(cell)) {
		            Date date = cell.getDateCellValue();
		            cellDateValue = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		        } else {
		            throw new IllegalArgumentException("Expected a date in Stock Date column but found a numeric value.");
		        }
		    } else if (cell.getCellType() == CellType.STRING) {
		    	cellDateValue = LocalDate.parse(cell.getStringCellValue(), DateTimeFormatter.ofPattern("M/d/yyyy"));
		    } else {
		        throw new IllegalArgumentException("Unsupported cell type for Stock Date.");
		    }
		} else {
		    throw new IllegalArgumentException("Stock Date cell is empty.");
		}
		return cellDateValue;
	}
	
    private String getCellValue(Cell cell) {
        return cell == null ? "" : cell.getStringCellValue().trim();
    }

    private String generateBarcode() {
        return "CFK" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private void createInventoryMovement(ProductEntity product, int quantity, String inventoryMovementType) {
    	InventoryMovementTypeEntity inventoryMovementTypeEntity = inventoryMovementsTypeRepository
    	        .findByMovementType(inventoryMovementType)
    	        .orElseThrow(() -> new IllegalStateException("Movement type not found: " + inventoryMovementType));
    	
    	InventoryMovementsEntity inventoryMovementEntity = new InventoryMovementsEntity();
    	inventoryMovementEntity.setProduct(product);
    	inventoryMovementEntity.setQuantityChange(quantity);
    	inventoryMovementEntity.setInventoryMovementType(inventoryMovementTypeEntity);
    	inventoryMovementEntity.setMovementDate(LocalDate.now());
    	
    	inventoryMovementsRepository.save(inventoryMovementEntity);
    }
	
	private String getCellStringValue(Row row, int col) {
	    Cell cell = row.getCell(col);
	    return cell != null ? cell.toString().trim() : null;
	}

	private double getCellNumericValue(Row row, int col) {
	    Cell cell = row.getCell(col);
	    if (cell == null) return 0.0;
	    if (cell.getCellType() == CellType.NUMERIC) return cell.getNumericCellValue();
	    try {
	        return Double.parseDouble(cell.toString().trim());
	    } catch (NumberFormatException e) {
	        return 0.0;
	    }
	}
}
