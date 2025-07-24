package com.cityfashionpos.service;

import java.time.LocalDateTime;
import java.util.List;

import com.cityfashionpos.dto.InvoiceRequest;
import com.cityfashionpos.response.InvoicePrintResponse;
import com.cityfashionpos.response.PastInvoiceResponse;

public interface InvoiceService {
	// Long createInvoice(InvoiceRequest request);
	InvoicePrintResponse createInvoice(InvoiceRequest request);

	List<PastInvoiceResponse> getFilteredInvoices(Long customerId, String invoiceNumber, String fromDate,
			String toDate, Integer paymentModeId, String paymentStatus);
	
	InvoicePrintResponse getInvoicePrintDetails(Long invoiceId);

}
