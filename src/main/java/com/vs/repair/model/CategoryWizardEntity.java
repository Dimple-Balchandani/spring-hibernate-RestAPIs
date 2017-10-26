package com.vs.repair.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "category_wizard")
public class CategoryWizardEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "category_wizard_id")
	private long id;

	@Column(name = "basic_pricing")
	private long basicPricing;

	@Column(name = "data")
	private String wizardData;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	private CategoryEntity categoryEntity;

	public CategoryWizardEntity() {
		super();
	}

	public CategoryWizardEntity(long basicPricing, String wizardData, CategoryEntity categoryEntity) {
		super();
		this.basicPricing = basicPricing;
		this.wizardData = wizardData;
		this.categoryEntity = categoryEntity;
	}

	public CategoryEntity getCategoryEntity() {
		return categoryEntity;
	}

	public void setCategoryEntity(CategoryEntity categoryEntity) {
		this.categoryEntity = categoryEntity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBasicPricing() {
		return basicPricing;
	}

	public void setBasicPricing(long basicPricing) {
		this.basicPricing = basicPricing;
	}

	public String getWizardData() {
		return wizardData;
	}

	public void setWizardData(String wizardData) {
		this.wizardData = wizardData;
	}

	@Override
	public String toString() {
		return "CategoryWizardEntity [id=" + id + ", basicPricing=" + basicPricing + ", wizardData=" + wizardData
				+ ", categoryEntity=" + categoryEntity + "]";
	}

}
