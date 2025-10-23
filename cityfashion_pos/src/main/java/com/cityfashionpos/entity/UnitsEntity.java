package com.cityfashionpos.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "units")
public class UnitsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unit_name", nullable = false, length = 100)
    private String unitName;

    @Column(name = "unit_abbr", nullable = false, length = 50)
    private String unitAbbr;

    @Column(name = "label", nullable = false, length = 150)
    private String label;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Default constructor
    public UnitsEntity() {
    }

    // Constructor with parameters
    public UnitsEntity(String unitName, String unitAbbr, String label) {
        this.unitName = unitName;
        this.unitAbbr = unitAbbr;
        this.label = label;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitAbbr() {
        return unitAbbr;
    }

    public void setUnitAbbr(String unitAbbr) {
        this.unitAbbr = unitAbbr;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "UnitsEntity{" +
                "id=" + id +
                ", unitName='" + unitName + '\'' +
                ", unitAbbr='" + unitAbbr + '\'' +
                ", label='" + label + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
