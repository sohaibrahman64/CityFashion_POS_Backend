package com.cityfashionpos.service.impl;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityfashionpos.entity.BarcodeEntity;
import com.cityfashionpos.entity.BarcodeRequest;
import com.cityfashionpos.entity.BarcodeRequest.BarcodeData;
import com.cityfashionpos.repository.BarcodeRepository;
import com.cityfashionpos.repository.ProductRepository;
import com.cityfashionpos.response.BarcodeResponse;
import com.cityfashionpos.service.BarcodeService;
import com.cityfashionpos.utils.BarcodeUtil;

@Service
@Transactional
public class BarcodeServiceImpl implements BarcodeService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private BarcodeRepository barcodeRepository;

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	@Override
	public String generateNextBarcode() {
		Long maxId = productRepository.findMaxProductId();
		Long nextId = (maxId == null) ? 1L : maxId + 1;
		String newBarcode = BarcodeUtil.generateFromId(nextId);

		// Double-check uniqueness
		while (productRepository.existsByBarcode(newBarcode)) {
			nextId++;
			newBarcode = BarcodeUtil.generateFromId(nextId);
		}

		return newBarcode;
	}

	@Override
	public BarcodeResponse saveBarcodes(BarcodeRequest request) {
		try {
			List<BarcodeEntity> savedBarcodes = new ArrayList<>();
			List<BarcodeResponse.BarcodeData> responseData = new ArrayList<>();

			for (BarcodeRequest.BarcodeData barcodeData : request.getBarcodes()) {
				// Check if product code already exists
				if (barcodeRepository.existsByProductCode(barcodeData.getProductCode())) {
					return new BarcodeResponse(false,
							"Product code '" + barcodeData.getProductCode() + "' already exists");
				}

				// Create new barcode entity
				BarcodeEntity barcode = new BarcodeEntity(barcodeData.getProductName(), barcodeData.getProductCode(),
						barcodeData.getNumLabels(), barcodeData.getHeader(), barcodeData.getLine1(),
						barcodeData.getLine2(), request.getPrinter(), request.getSize());

				// Save to database
				BarcodeEntity savedBarcode = barcodeRepository.save(barcode);
				savedBarcodes.add(savedBarcode);

				// Create response data
				BarcodeResponse.BarcodeData responseBarcode = new BarcodeResponse.BarcodeData(savedBarcode.getId(),
						savedBarcode.getProductName(), savedBarcode.getProductCode(), savedBarcode.getNumLabels(),
						savedBarcode.getHeader(), savedBarcode.getLine1(), savedBarcode.getLine2(),
						savedBarcode.getPrinterType(), savedBarcode.getSizeOption(),
						savedBarcode.getCreatedAt().format(DATE_FORMATTER));
				responseData.add(responseBarcode);
			}

			String message = "Successfully saved " + savedBarcodes.size() + " barcode(s)";
			return new BarcodeResponse(true, message, responseData);

		} catch (Exception e) {
			return new BarcodeResponse(false, "Error saving barcodes: " + e.getMessage());
		}
	}

	@Override
	public BarcodeResponse getAllBarcodes() {
		try {
			List<BarcodeEntity> barcodes = barcodeRepository.findAll();
			List<BarcodeResponse.BarcodeData> responseData = new ArrayList<>();

			for (BarcodeEntity barcode : barcodes) {
				BarcodeResponse.BarcodeData responseBarcode = new BarcodeResponse.BarcodeData(barcode.getId(),
						barcode.getProductName(), barcode.getProductCode(), barcode.getNumLabels(), barcode.getHeader(),
						barcode.getLine1(), barcode.getLine2(), barcode.getPrinterType(), barcode.getSizeOption(),
						barcode.getCreatedAt().format(DATE_FORMATTER));
				responseData.add(responseBarcode);
			}

			return new BarcodeResponse(true, "Retrieved " + responseData.size() + " barcode(s)", responseData);

		} catch (Exception e) {
			return new BarcodeResponse(false, "Error retrieving barcodes: " + e.getMessage());
		}
	}

	@Override
	public BarcodeResponse getBarcodeById(Long id) {
		try {
			Optional<BarcodeEntity> barcodeOpt = barcodeRepository.findById(id);

			if (barcodeOpt.isPresent()) {
				BarcodeEntity barcode = barcodeOpt.get();
				BarcodeResponse.BarcodeData responseBarcode = new BarcodeResponse.BarcodeData(barcode.getId(),
						barcode.getProductName(), barcode.getProductCode(), barcode.getNumLabels(), barcode.getHeader(),
						barcode.getLine1(), barcode.getLine2(), barcode.getPrinterType(), barcode.getSizeOption(),
						barcode.getCreatedAt().format(DATE_FORMATTER));

				List<BarcodeResponse.BarcodeData> responseData = new ArrayList<>();
				responseData.add(responseBarcode);

				return new BarcodeResponse(true, "Barcode found", responseData);
			} else {
				return new BarcodeResponse(false, "Barcode not found with ID: " + id);
			}

		} catch (Exception e) {
			return new BarcodeResponse(false, "Error retrieving barcode: " + e.getMessage());
		}
	}

	@Override
	public BarcodeResponse getBarcodeByProductCode(String productCode) {
		try {
			Optional<BarcodeEntity> barcodeOpt = barcodeRepository.findByProductCode(productCode);

			if (barcodeOpt.isPresent()) {
				BarcodeEntity barcode = barcodeOpt.get();
				BarcodeResponse.BarcodeData responseBarcode = new BarcodeResponse.BarcodeData(barcode.getId(),
						barcode.getProductName(), barcode.getProductCode(), barcode.getNumLabels(), barcode.getHeader(),
						barcode.getLine1(), barcode.getLine2(), barcode.getPrinterType(), barcode.getSizeOption(),
						barcode.getCreatedAt().format(DATE_FORMATTER));

				List<BarcodeResponse.BarcodeData> responseData = new ArrayList<>();
				responseData.add(responseBarcode);

				return new BarcodeResponse(true, "Barcode found", responseData);
			} else {
				return new BarcodeResponse(false, "Barcode not found with product code: " + productCode);
			}

		} catch (Exception e) {
			return new BarcodeResponse(false, "Error retrieving barcode: " + e.getMessage());
		}
	}

	@Override
	public BarcodeResponse deleteBarcodeById(Long id) {
		try {
			if (barcodeRepository.existsById(id)) {
				barcodeRepository.deleteById(id);
				return new BarcodeResponse(true, "Barcode deleted successfully");
			} else {
				return new BarcodeResponse(false, "Barcode not found with ID: " + id);
			}

		} catch (Exception e) {
			return new BarcodeResponse(false, "Error deleting barcode: " + e.getMessage());
		}
	}

	@Override
	public BarcodeResponse updateBarcode(Long id, BarcodeData barcodeData) {
        try {
            Optional<BarcodeEntity> barcodeOpt = barcodeRepository.findById(id);
            
            if (barcodeOpt.isPresent()) {
                BarcodeEntity barcode = barcodeOpt.get();
                
                // Update fields
                barcode.setProductName(barcodeData.getProductName());
                barcode.setProductCode(barcodeData.getProductCode());
                barcode.setNumLabels(barcodeData.getNumLabels());
                barcode.setHeader(barcodeData.getHeader());
                barcode.setLine1(barcodeData.getLine1());
                barcode.setLine2(barcodeData.getLine2());
                
                BarcodeEntity savedBarcode = barcodeRepository.save(barcode);
                
                BarcodeResponse.BarcodeData responseBarcode = new BarcodeResponse.BarcodeData(
                    savedBarcode.getId(),
                    savedBarcode.getProductName(),
                    savedBarcode.getProductCode(),
                    savedBarcode.getNumLabels(),
                    savedBarcode.getHeader(),
                    savedBarcode.getLine1(),
                    savedBarcode.getLine2(),
                    savedBarcode.getPrinterType(),
                    savedBarcode.getSizeOption(),
                    savedBarcode.getCreatedAt().format(DATE_FORMATTER)
                );
                
                List<BarcodeResponse.BarcodeData> responseData = new ArrayList<>();
                responseData.add(responseBarcode);
                
                return new BarcodeResponse(true, "Barcode updated successfully", responseData);
            } else {
                return new BarcodeResponse(false, "Barcode not found with ID: " + id);
            }
            
        } catch (Exception e) {
            return new BarcodeResponse(false, "Error updating barcode: " + e.getMessage());
        }
    }
}
