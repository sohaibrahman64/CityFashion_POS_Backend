package com.cityfashionpos.entity;

import java.util.List;

public class BarcodeRequest {
    private List<BarcodeData> barcodes;
    private String printer;
    private String size;
    
    // Default constructor
    public BarcodeRequest() {}
    
    // Constructor with fields
    public BarcodeRequest(List<BarcodeData> barcodes, String printer, String size) {
        this.barcodes = barcodes;
        this.printer = printer;
        this.size = size;
    }
    
    // Getters and Setters
    public List<BarcodeData> getBarcodes() {
        return barcodes;
    }
    
    public void setBarcodes(List<BarcodeData> barcodes) {
        this.barcodes = barcodes;
    }
    
    public String getPrinter() {
        return printer;
    }
    
    public void setPrinter(String printer) {
        this.printer = printer;
    }
    
    public String getSize() {
        return size;
    }
    
    public void setSize(String size) {
        this.size = size;
    }
    
    @Override
    public String toString() {
        return "BarcodeRequest{" +
                "barcodes=" + barcodes +
                ", printer='" + printer + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
    
    // Inner class for individual barcode data
    public static class BarcodeData {
        private String productName;
        private String productCode;
        private Integer numLabels;
        private String header;
        private String line1;
        private String line2;
        
        // Default constructor
        public BarcodeData() {}
        
        // Constructor with fields
        public BarcodeData(String productName, String productCode, Integer numLabels, 
                          String header, String line1, String line2) {
            this.productName = productName;
            this.productCode = productCode;
            this.numLabels = numLabels;
            this.header = header;
            this.line1 = line1;
            this.line2 = line2;
        }
        
        // Getters and Setters
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
        
        @Override
        public String toString() {
            return "BarcodeData{" +
                    "productName='" + productName + '\'' +
                    ", productCode='" + productCode + '\'' +
                    ", numLabels=" + numLabels +
                    ", header='" + header + '\'' +
                    ", line1='" + line1 + '\'' +
                    ", line2='" + line2 + '\'' +
                    '}';
        }
    }
}
