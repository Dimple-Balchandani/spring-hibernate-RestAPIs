package com.vs.repair.dao;

import java.util.List;

import com.vs.repair.model.UserEntity;

public interface UserDao {
	boolean addUser(UserEntity request);
	
	void deleteUser(UserEntity request);
	
	List<UserEntity> list();
	
	void updateUser(long id, UserEntity request);

	UserEntity findById(long id);
}
