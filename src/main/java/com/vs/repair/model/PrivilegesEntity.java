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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "privileges")
public class PrivilegesEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "privilege_id")
	private long id;
	
	@Column(name = "user_role")
	private String role;
	
	@Column(name = "add_permission")
	private boolean add;

	@Column(name = "delete_permission")
	private boolean delete;
	
	@Column(name = "view_permission")
	private boolean view;

	@Column(name = "update_permission")
	private boolean update;
		
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "privilege", cascade = CascadeType.ALL)
	private Set<UserEntity> userEntity = new HashSet<UserEntity>();

	public Set<UserEntity> getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(Set<UserEntity> userEntity) {
		this.userEntity = userEntity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean getAdd() {
		return add;
	}

	public void setAdd(boolean add) {
		this.add = add;
	}

	public boolean getDelete() {
		return delete;
	}


	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public boolean getView() {
		return view;
	}


	public void setView(boolean view) {
		this.view = view;
	}

	public boolean getUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}


	@Override
	public String toString() {
		return "PrivilegesEntity [id=" + id + ", role=" + role + ", add=" + add + ", delete=" + delete + ", view="
				+ view + ", update=" + update + ", userEntity=" + userEntity + "]";
	}
}
