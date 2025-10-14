package com.cityfashionpos.dto;

import java.time.LocalDateTime;

/**
 * DTO for State response
 */
public class StateDto {

    private Long id;
    private String state;
    private LocalDateTime createdAt;
    private Integer isActive;

    // Default constructor
    public StateDto() {
    }

    // Constructor with all fields
    public StateDto(Long id, String state, LocalDateTime createdAt, Integer isActive) {
        this.id = id;
        this.state = state;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
        return "StateDto{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", createdAt=" + createdAt +
                ", isActive=" + isActive +
                '}';
    }
}
