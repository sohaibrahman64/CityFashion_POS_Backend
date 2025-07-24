package com.cityfashionpos.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="inventory_movements")
public class InventoryMovementsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private ProductEntity product;
	
	@Column(name="quantity_change", nullable=false)
	private Integer quantityChange;
	
	@ManyToOne
	@JoinColumn(name="movement_type_id", nullable=false)
	private InventoryMovementTypeEntity inventoryMovementType;
	
	@Column(name="movement_date", nullable=false)
	private LocalDate movementDate;
	
	@Column(name="reference_id", nullable=false)
	private String referenceId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public Integer getQuantityChange() {
		return quantityChange;
	}

	public void setQuantityChange(Integer quantityChange) {
		this.quantityChange = quantityChange;
	}

	public InventoryMovementTypeEntity getInventoryMovementType() {
		return inventoryMovementType;
	}

	public void setInventoryMovementType(InventoryMovementTypeEntity inventoryMovementType) {
		this.inventoryMovementType = inventoryMovementType;
	}

	public LocalDate getMovementDate() {
		return movementDate;
	}

	public void setMovementDate(LocalDate movementDate) {
		this.movementDate = movementDate;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	
}
