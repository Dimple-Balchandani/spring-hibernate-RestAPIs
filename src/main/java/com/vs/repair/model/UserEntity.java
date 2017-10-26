package com.vs.repair.model;

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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vs.repair.model.request.UserRequest;

@Entity
@Table(name = "user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private long id;
	
	@Column(name = "user_name")
	private String name;
	
	@Column(name = "user_address")
	private String address;
	
	@Column(name = "user_phone")
	private long phone;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "privilege_id", nullable = false)
	private PrivilegesEntity privilege;

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "providerUser", cascade = CascadeType.ALL)
	private Set<OrderEntity> providerOrders = new HashSet<>();

	@JsonIgnore	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "shipperUser", cascade = CascadeType.ALL)
	private Set<OrderEntity> shipperOrders = new HashSet<>();

	public UserEntity (){
		super();
	}
	
	public UserEntity (UserRequest request, PrivilegesEntity privilege){
		super();
		this.name = request.getName();
		this.address =request.getAddress();
		this.phone = request.getPhone();
		this.privilege = privilege;
	}
	
	public PrivilegesEntity getPrivilege() {
		return privilege;
	}

	public void setPrivilege(PrivilegesEntity privilege) {
		this.privilege = privilege;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}
	
	public Set<OrderEntity> getProviderOrders(){
		return providerOrders;
	}
	
	public void setProviderOrders(Set<OrderEntity> orders){
		this.providerOrders = orders;
	}

	public Set<OrderEntity> getShipperOrders(){
		return shipperOrders;
	}
	
	public void setShipperOrders(Set<OrderEntity> orders){
		this.shipperOrders = orders;
	}
	
	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", name=" + name + ",  address=" + address + ", phone="
				+ phone + ", privilege_id=" + privilege + "]";
	}
}
