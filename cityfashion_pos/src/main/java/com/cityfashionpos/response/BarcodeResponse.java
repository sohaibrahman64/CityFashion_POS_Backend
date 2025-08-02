package com.cityfashionpos.response;

import java.util.List;

public class BarcodeResponse {
    
    private boolean success;
    private String message;
    private List<BarcodeData> savedBarcodes;
    private int totalSaved;
    
    // Default constructor
    public BarcodeResponse() {}
    
    // Constructor for success response
    public BarcodeResponse(boolean success, String message, List<BarcodeData> savedBarcodes) {
        this.success = success;
        this.message = message;
        this.savedBarcodes = savedBarcodes;
        this.totalSaved = savedBarcodes != null ? savedBarcodes.size() : 0;
    }
    
    // Constructor for error response
    public BarcodeResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.savedBarcodes = null;
        this.totalSaved = 0;
    }
    
    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public List<BarcodeData> getSavedBarcodes() {
        return savedBarcodes;
    }
    
    public void setSavedBarcodes(List<BarcodeData> savedBarcodes) {
        this.savedBarcodes = savedBarcodes;
        this.totalSaved = savedBarcodes != null ? savedBarcodes.size() : 0;
    }
    
    public int getTotalSaved() {
        return totalSaved;
    }
    
    public void setTotalSaved(int totalSaved) {
        this.totalSaved = totalSaved;
    }
    
    @Override
    public String toString() {
        return "BarcodeResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", savedBarcodes=" + savedBarcodes +
                ", totalSaved=" + totalSaved +
                '}';
    }
    
    // Inner class for saved barcode data
    public static class BarcodeData {
        private Long id;
        private String productName;
        private String productCode;
        private Integer numLabels;
        private String header;
        private String line1;
        private String line2;
        private String printerType;
        private String sizeOption;
        private String createdAt;
        
        // Default constructor
        public BarcodeData() {}
        
        // Constructor with fields
        public BarcodeData(Long id, String productName, String productCode, Integer numLabels, 
                          String header, String line1, String line2, 
                          String printerType, String sizeOption, String createdAt) {
            this.id = id;
            this.productName = productName;
            this.productCode = productCode;
            this.numLabels = numLabels;
            this.header = header;
            this.line1 = line1;
            this.line2 = line2;
            this.printerType = printerType;
            this.sizeOption = sizeOption;
            this.createdAt = createdAt;
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
        
        public String getCreatedAt() {
            return createdAt;
        }
        
        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }
        
        @Override
        public String toString() {
            return "BarcodeData{" +
                    "id=" + id +
                    ", productName='" + productName + '\'' +
                    ", productCode='" + productCode + '\'' +
                    ", numLabels=" + numLabels +
                    ", header='" + header + '\'' +
                    ", line1='" + line1 + '\'' +
                    ", line2='" + line2 + '\'' +
                    ", printerType='" + printerType + '\'' +
                    ", sizeOption='" + sizeOption + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    '}';
        }
    }
}