package com.cityfashionpos.dto;

import java.time.LocalDateTime;

/**
 * DTO for GST Type response
 */
public class GstTypeDto {

    private Long id;
    private String gstType;
    private LocalDateTime createdAt;
    private Integer isActive;

    // Default constructor
    public GstTypeDto() {
    }

    // Constructor with all fields
    public GstTypeDto(Long id, String gstType, LocalDateTime createdAt, Integer isActive) {
        this.id = id;
        this.gstType = gstType;
        this.createdAt = createdAt;
        this.isActive = isActive;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGstType() {
        return gstType;
    }

    public void setGstType(String gstType) {
        this.gstType = gstType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "GstTypeDto{" +
                "id=" + id +
                ", gstType='" + gstType + '\'' +
                ", createdAt=" + createdAt +
                ", isActive=" + isActive +
                '}';
    }
}
