package com.cityfashionpos.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "payment_in_history")
public class PaymentInHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "link_payment_to_txn_id")
    private LinkPaymentInTxnEntity linkPaymentInTxn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LinkPaymentInTxnEntity getLinkPaymentInTxn() {
        return linkPaymentInTxn;
    }

    public void setLinkPaymentInTxn(LinkPaymentInTxnEntity linkPaymentInTxn) {
        this.linkPaymentInTxn = linkPaymentInTxn;
    }

}
