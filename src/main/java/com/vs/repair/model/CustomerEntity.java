package com.vs.repair.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "customer")
public class CustomerEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_id")
	private long id;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "zip")
	private String zip;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "last_login")
	private long lastLoginTime;

	@Column(name = "auth_token")
	private String authToken;

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
	private Set<ItemEntity> item = new HashSet<>();

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
	private Set<FeedbackEntity> feedback = new HashSet<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public Set<ItemEntity> getItem() {
		return item;
	}

	public void setItem(Set<ItemEntity> item) {
		this.item = item;
	}

	public Set<FeedbackEntity> getFeedback() {
		return feedback;
	}

	public void setFeedback(Set<FeedbackEntity> feedback) {
		this.feedback = feedback;
	}

	@Override
	public String toString() {
		return "CustomerEntity [id=" + id + ", userName=" + userName + ", address=" + address + ", city=" + city
				+ ", zip=" + zip + ", phone=" + phone + ", email=" + email + ", password=" + password
				+ ", lastLoginTime=" + lastLoginTime + ", authToken=" + authToken + ", item=" + item + ", feedback="
				+ feedback + "]";
	}

}
