package com.vs.repair.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vs.repair.model.PrivilegesEntity;
import com.vs.repair.model.UserEntity;

public class UserRequest extends UserEntity{

	private long privilegeId;

	public long getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(long privilegeId) {
		this.privilegeId = privilegeId;
	}
	
	@JsonIgnore
	@Override
	public PrivilegesEntity getPrivilege() {
		return super.getPrivilege();
	}
	
}
