package com.vs.repair.dao;

import java.util.List;

import com.vs.repair.model.PrivilegesEntity;

public interface PrivilegesDao {

	boolean addPrivilege(PrivilegesEntity request);
	
	void deletePrivilege(PrivilegesEntity request);
	
	List<PrivilegesEntity> list();
	
	void updatePrivilege(long id, PrivilegesEntity request);

	PrivilegesEntity findById(long id);

}