package com.cityfashionpos.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.cityfashionpos.entity.InvoiceEntity;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long>, JpaSpecificationExecutor<InvoiceEntity>{
	@Query(value="SELECT MAX(id) FROM sales_invoice", nativeQuery=true)
	Long findMaxId();
	
	public static Specification<InvoiceEntity> getInvoiceFilterSpec(Long customerId, String invoiceNumber, LocalDate fromDate,
			LocalDate toDate, Integer paymentModeId, String paymentStatus) {
	    return (root, query, cb) -> {
	        List<Predicate> predicates = new ArrayList<>();
	        
	        if (customerId != null) {
	            predicates.add(cb.equal(root.get("customer").get("id"), customerId));
	        }
	        if (invoiceNumber != null && !invoiceNumber.isEmpty()) {
	            predicates.add(cb.like(root.get("invoiceNumber"), "%" + invoiceNumber + "%"));
	        }
	        if (fromDate != null && toDate != null) {
	            predicates.add(cb.between(root.get("invoiceDate"), fromDate, toDate));
	        }
	        if (paymentModeId != null) {
	            predicates.add(cb.equal(root.get("paymentMode").get("id"), paymentModeId));
	        }
	        if (paymentStatus != null && !paymentStatus.isEmpty()) {
	            predicates.add(cb.equal(root.get("paymentStatus").get("statusName"), paymentStatus));
	        }

	        return cb.and(predicates.toArray(new Predicate[0]));
	    };
	}
}
