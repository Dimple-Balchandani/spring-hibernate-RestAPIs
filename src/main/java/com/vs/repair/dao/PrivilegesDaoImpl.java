package com.vs.repair.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vs.repair.model.PrivilegesEntity;

@Repository
public class PrivilegesDaoImpl extends AbstractDao<Long, PrivilegesEntity> implements PrivilegesDao{

	public boolean addPrivilege(PrivilegesEntity privilege) {
		try {
			persist(privilege);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void deletePrivilege(PrivilegesEntity privilege) {
		delete(privilege);
	}
	
	@SuppressWarnings("unchecked")
	public List<PrivilegesEntity> list() {
		return entityManager.createQuery("SELECT u from PrivilegesEntity u").getResultList();
	}

	public void updatePrivilege(long id, PrivilegesEntity privilege) {
		privilege.setId(id);
		update(privilege);
	}

	public PrivilegesEntity findById(long id) {
		PrivilegesEntity entity = getByKey(id);
		return entity;
	}
	
}