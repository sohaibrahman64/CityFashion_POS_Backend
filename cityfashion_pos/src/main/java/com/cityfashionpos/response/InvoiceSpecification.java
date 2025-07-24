package com.cityfashionpos.response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.cityfashionpos.entity.InvoiceEntity;
import com.cityfashionpos.entity.InvoicePaymentEntity;

public class InvoiceSpecification {

    public static Specification<InvoiceEntity> build(
            Long customerId,
            String invoiceNumber,
            String fromDate,
            String toDate,
            Integer paymentModeId,
            String paymentStatus
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            if (customerId != null) {
                predicates.add(cb.equal(root.get("customerId"), customerId));
            }
            if (invoiceNumber != null && !invoiceNumber.isEmpty()) {
                predicates.add(cb.like(root.get("invoiceNumber"), "%" + invoiceNumber + "%"));
            }
            if (fromDate != null && toDate != null) {
            	LocalDate from = fromDate != null ? LocalDate.parse(fromDate) : null;
            	LocalDate to = toDate != null ? LocalDate.parse(toDate) : null;
                predicates.add(cb.between(root.get("invoiceDate"), from, to));
            }
            if (paymentModeId != null) {
            	Join<InvoiceEntity, InvoicePaymentEntity> paymentsJoin = root.join("payments");
                //predicates.add(cb.equal(root.get("payments").get("paymentMode").get("id"), paymentModeId));
            	predicates.add(cb.equal(paymentsJoin.get("paymentMode").get("id"), paymentModeId));
            }
            if (paymentStatus != null && !paymentStatus.isEmpty()) {
                predicates.add(cb.equal(root.get("paymentStatus").get("statusName"), paymentStatus));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
