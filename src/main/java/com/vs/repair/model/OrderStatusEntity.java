package com.vs.repair.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vs.repair.model.request.OrderStatusRequest;

@Entity
@Table(name = "order_status")
public class OrderStatusEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_status_id")
	private long id;

	@Column(name = "created_on", nullable = false)
	private long createdOn;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "status_id")
	private StatusEntity status;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private OrderEntity order;
	
	public OrderStatusEntity() {
		super();
	}

	public OrderStatusEntity(OrderStatusRequest request, OrderEntity order, StatusEntity status) {
		super();
		this.order = order;
		this.status = status;
	}

	public StatusEntity getStatus() {
		return status;
	}

	public void setStatus(StatusEntity status) {
		this.status = status;
	}

	public OrderEntity getOrder() {
		return order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(long createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "OrderStatusEntity [status=" + status + ", order=" + order + ", id=" + id + ", createdOn=" + createdOn
				+ "]";
	}

}
