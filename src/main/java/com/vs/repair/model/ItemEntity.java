package com.vs.repair.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vs.repair.model.request.ItemRequest;

@Entity
@Table(name = "item")
public class ItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "item_id")
	private long id;

	@Column(name = "item_name")
	private String name;

	@Column(name = "item_desc")
	private String desc;

	@Column(name = "item_image")
	private String image;

	private long createdOn = new Date().getTime();

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private CategoryEntity category;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private CustomerEntity customer;

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "item")
	private Set<FeedbackEntity> feedback = new HashSet<>();

	public ItemEntity() {
		super();
	}

	public ItemEntity(ItemRequest request, CategoryEntity category,
			CustomerEntity customer) {
		super();
		this.name = request.getName();
		this.desc = request.getDesc();
		this.image = request.getImage();
		this.category = category;
		this.customer = customer;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getItemName() {
		return name;
	}

	public void setItemName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(long createdOn) {
		this.createdOn = createdOn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public Set<FeedbackEntity> getFeedback() {
		return feedback;
	}

	public void setFeedback(Set<FeedbackEntity> feedback) {
		this.feedback = feedback;
	}

	@Override
	public String toString() {
		return "ItemEntity [id=" + id + ", name=" + name + ", desc=" + desc + ", image=" + image + ", createdOn="
				+ createdOn + ", category=" + category + ", customer=" + customer + "]";
	}

}
