package com.vs.repair.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vs.repair.model.UserEntity;

@Repository
public class UserDaoImpl extends AbstractDao<Long, UserEntity> implements UserDao{
	
	public boolean addUser(UserEntity user) {
		try {
			persist(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void deleteUser(UserEntity user) {
		delete(user);
	}
	
	@SuppressWarnings("unchecked")
	public List<UserEntity> list() {
		return entityManager.createQuery("SELECT u from UserEntity u").getResultList();
	}

	public void updateUser(long id, UserEntity user) {
		user.setId(id);
		update(user);
	}

	public UserEntity findById(long id) {
		UserEntity entity = getByKey(id);
		return entity;
		
	}
}
