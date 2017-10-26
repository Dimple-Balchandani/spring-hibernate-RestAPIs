package com.vs.repair.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vs.repair.model.request.FeedbackRequest;

@Entity
@Table(name = "feedback")
public class FeedbackEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "feedback_id")
	private long id;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", nullable = false)
	private CustomerEntity customer;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "item_id")
	private ItemEntity item;

	@Column(name = "description")
	private String description;

	@Column(name = "reply")
	private String reply;

	@Column(name = "type")
	private int type;

	@Column(name = "createdOn")
	private long createdOn;

	public FeedbackEntity (){
		super();
	}
	
	public FeedbackEntity (FeedbackRequest request, CustomerEntity customer, ItemEntity item){
		super();
		this.description = request.getDescription();
		this.reply = request.getReply();
		this.type = request.getType();
		this.customer = customer;
		this.item = item;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public ItemEntity getItem() {
		return item;
	}

	public void setItem(ItemEntity item) {
		this.item = item;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(long createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "CustomerFeedbackEntity [id=" + id + ", customer=" + customer + ", item=" + item + ", description="
				+ description + ", reply=" + reply + ", type=" + type + ", createdOn=" + createdOn + "]";
	}

}
