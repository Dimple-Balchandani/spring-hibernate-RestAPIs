package com.vs.repair.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "status")
public class StatusEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "status_id")
	private long id;

	@JsonIgnore
	@OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
	private Set<OrderStatusEntity> order = new HashSet<>();

	@Column(name = "status_name", nullable = false)
	private String status_name;

	@Column(name = "status_desc", nullable = false)
	private String status_desc;

	public String getStatus_desc() {
		return status_desc;
	}

	public void setStatus_desc(String status_desc) {
		this.status_desc = status_desc;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<OrderStatusEntity> getOrder() {
		return order;
	}

	public void setOrder(Set<OrderStatusEntity> order) {
		this.order = order;
	}

	public String getStatus_name() {
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}

	@Override
	public String toString() {
		return "StatusEntity [id=" + id + ", order=" + order + ", status_desc=" + status_desc + "]";
	}

}
