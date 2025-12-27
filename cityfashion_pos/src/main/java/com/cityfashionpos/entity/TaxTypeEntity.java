package com.cityfashionpos.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tax_type")
public class TaxTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tax_type", nullable = false, length = 100)
    private String taxType;

    @Column(name = "tax_type_code", nullable = false, length = 50)
    private String taxTypeCode;

    @Column(name = "created_at", nullable = false)
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    // Default constructor
    public TaxTypeEntity() {
    }

    // Constructor with parameters
    public TaxTypeEntity(String taxType, String taxTypeCode) {
        this.taxType = taxType;
        this.taxTypeCode = taxTypeCode;
        this.createdAt = LocalDateTime.now().toString();
        this.updatedAt = LocalDateTime.now().toString();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public String getTaxTypeCode() {
        return taxTypeCode;
    }

    public void setTaxTypeCode(String taxTypeCode) {
        this.taxTypeCode = taxTypeCode;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now().toString();
        updatedAt = LocalDateTime.now().toString();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now().toString();
    }

    @Override
    public String toString() {
        return "TaxTypeEntity{" +
                "id=" + id +
                ", taxType='" + taxType + '\'' +
                ", taxTypeCode='" + taxTypeCode + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
