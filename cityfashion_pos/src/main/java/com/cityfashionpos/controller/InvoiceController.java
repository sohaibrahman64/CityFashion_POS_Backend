package com.cityfashionpos.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cityfashionpos.dto.InvoiceRequest;
import com.cityfashionpos.repository.InvoiceRepository;
import com.cityfashionpos.response.InvoicePrintResponse;
import com.cityfashionpos.response.PastInvoiceResponse;
import com.cityfashionpos.service.InvoiceService;

@RestController
@RequestMapping("/api/invoice")
@CrossOrigin(origins = "*")
public class InvoiceController {
	@Autowired
	InvoiceRepository invoiceRepository;
	
	@Autowired
    private InvoiceService invoiceService;

    @PostMapping("/createInvoice")
    public ResponseEntity<InvoicePrintResponse> createInvoice(@RequestBody InvoiceRequest invoiceRequest) {
    	InvoicePrintResponse invoiceResponse = invoiceService.createInvoice(invoiceRequest);
        return ResponseEntity.ok(invoiceResponse);
    }
    
    @GetMapping("/getInvoicePrintResponse/{invoiceId}")
    public ResponseEntity<InvoicePrintResponse> getInvoicePrintResponse(@PathVariable Long invoiceId) {
    	InvoicePrintResponse invoiceResponse =  invoiceService.getInvoicePrintDetails(invoiceId);
    	return ResponseEntity.ok(invoiceResponse);
    }

	@GetMapping("/generateInvoiceNumber")
	public ResponseEntity<String> generateInvoiceNumber() {
		Long latestId = invoiceRepository.findMaxId();
		if (latestId == null) {
			latestId = 0L;
		}
		String nextInvoiceNumber = String.format("CFK-%05d", latestId + 1);
		return ResponseEntity.ok(nextInvoiceNumber);

	}
	
	@GetMapping("/getPastInvoices")
	public ResponseEntity<List<PastInvoiceResponse>> getFilteredInvoices(
	        @RequestParam(required = false) Long customerId,
	        @RequestParam(required = false) String invoiceNumber,
	        @RequestParam(required = false) String fromDate,
	        @RequestParam(required = false) String toDate,
	        @RequestParam(required = false) Integer paymentModeId,
	        @RequestParam(required = false) String paymentStatus
	) {
	    List<PastInvoiceResponse> invoices = invoiceService.getFilteredInvoices(
	            customerId, invoiceNumber, fromDate, toDate, paymentModeId, paymentStatus
	    );
	    return ResponseEntity.ok(invoices);
	}

}
