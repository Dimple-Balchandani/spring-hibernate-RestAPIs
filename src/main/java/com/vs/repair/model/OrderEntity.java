package com.vs.repair.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vs.repair.model.request.OrderRequest;

@SuppressWarnings("serial")
@Entity
@Table(name = "item_order")
public class OrderEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id")
	private long id;
	
	@JsonIgnore
    @OneToMany(mappedBy="order")  
	private Set<OrderStatusEntity> status = new HashSet<>(); 

	@Column(name = "estimated_time")
	private long estimatedTime;

	@Column(name = "estimated_price")
	private long estimatedPrice;

	@JsonIgnore
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL )
	@JoinColumn(name = "item_id")
	private ItemEntity item;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "provider_id")
	private UserEntity providerUser;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "shipper_id")
	private UserEntity shipperUser;


	@Column(name = "created_on", nullable = false)
	private long createdOn;
	
	public OrderEntity(){
		super();
	}
	
	public OrderEntity(OrderRequest request,UserEntity shipperUser,UserEntity providerUser){
		super();
		this.id = request.getId();
		this.estimatedPrice = request.getEstimatedPrice();
		this.estimatedTime = request.getEstimatedTime();
		this.providerUser = providerUser;
		this.shipperUser = shipperUser;
		
	}
	
	public long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(long createdOn) {
		this.createdOn = createdOn;
	}
	
	public Set<OrderStatusEntity> getStatus() {
		return status;
	}

	public void setStatus(Set<OrderStatusEntity> status) {
		this.status = status;
	}

	public long getEstimatedTime() {
		return estimatedTime;
	}

	public void setEstimatedTime(long estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	public long getEstimatedPrice() {
		return estimatedPrice;
	}

	public void setEstimatedPrice(long estimatedPrice) {
		this.estimatedPrice = estimatedPrice;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ItemEntity getItem() {
		return item;
	}

	public void setItem(ItemEntity item) {
		this.item = item;
	}

	public UserEntity getProviderUser() {
		return providerUser;
	}

	public void setProviderUser(UserEntity providerUser) {
		this.providerUser = providerUser;
	}

	public UserEntity getShipperUser() {
		return shipperUser;
	}

	public void setShipperUser(UserEntity shipperUser) {
		this.shipperUser = shipperUser;
	}

	@Override
	public String toString() {
		return "OrderEntity [id=" + id + ", status=" + status + ", estimatedTime=" + estimatedTime + ", estimatedPrice="
				+ estimatedPrice + ", createdOn=" + createdOn + ", item=" + item + ", providerUser=" + providerUser + ", shipperUser=" + shipperUser
				+ "]";
	}

}
