package com.cityfashionpos.entity;

import com.cityfashionpos.model.TaxRateType;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tax_rates")
public class TaxRateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "label", nullable = false, length = 50)
    private String label;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TaxRateType type;

    @Column(name = "rate", nullable = false, precision = 5, scale = 2)
    private BigDecimal rate;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @Column(name = "idx")
    private Long index;

    // Default constructor
    public TaxRateEntity() {
    }

    // Constructor with parameters
    public TaxRateEntity(String label, TaxRateType type, BigDecimal rate, Boolean active, Long index) {
        this.label = label;
        this.type = type;
        this.rate = rate;
        this.active = active;
        this.index = index;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public TaxRateType getType() {
        return type;
    }

    public void setType(TaxRateType type) {
        this.type = type;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "TaxRateEntity{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", type=" + type +
                ", rate=" + rate +
                ", active=" + active +
                '}';
    }
}
