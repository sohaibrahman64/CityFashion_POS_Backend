package com.cityfashionpos.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "barcodes")
public class BarcodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "product_name", nullable = false)
    private String productName;
    
    @Column(name = "product_code", nullable = false, unique = true)
    private String productCode;
    
    @Column(name = "num_labels", nullable = false)
    private Integer numLabels;
    
    @Column(name = "header")
    private String header;
    
    @Column(name = "line1")
    private String line1;
    
    @Column(name = "line2")
    private String line2;
    
    @Column(name = "printer_type")
    private String printerType;
    
    @Column(name = "size_option")
    private String sizeOption;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Default constructor
    public BarcodeEntity() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    // Constructor with fields
    public BarcodeEntity(String productName, String productCode, Integer numLabels, 
                   String header, String line1, String line2, 
                   String printerType, String sizeOption) {
        this();
        this.productName = productName;
        this.productCode = productCode;
        this.numLabels = numLabels;
        this.header = header;
        this.line1 = line1;
        this.line2 = line2;
        this.printerType = printerType;
        this.sizeOption = sizeOption;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public String getProductCode() {
        return productCode;
    }
    
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    
    public Integer getNumLabels() {
        return numLabels;
    }
    
    public void setNumLabels(Integer numLabels) {
        this.numLabels = numLabels;
    }
    
    public String getHeader() {
        return header;
    }
    
    public void setHeader(String header) {
        this.header = header;
    }
    
    public String getLine1() {
        return line1;
    }
    
    public void setLine1(String line1) {
        this.line1 = line1;
    }
    
    public String getLine2() {
        return line2;
    }
    
    public void setLine2(String line2) {
        this.line2 = line2;
    }
    
    public String getPrinterType() {
        return printerType;
    }
    
    public void setPrinterType(String printerType) {
        this.printerType = printerType;
    }
    
    public String getSizeOption() {
        return sizeOption;
    }
    
    public void setSizeOption(String sizeOption) {
        this.sizeOption = sizeOption;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
    
    @Override
    public String toString() {
        return "Barcode{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productCode='" + productCode + '\'' +
                ", numLabels=" + numLabels +
                ", header='" + header + '\'' +
                ", line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                ", printerType='" + printerType + '\'' +
                ", sizeOption='" + sizeOption + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
