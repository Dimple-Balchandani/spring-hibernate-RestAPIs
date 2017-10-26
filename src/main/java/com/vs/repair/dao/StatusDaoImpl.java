package com.vs.repair.dao;

import org.springframework.stereotype.Repository;

import com.vs.repair.model.StatusEntity;

@Repository
public class StatusDaoImpl extends AbstractDao<Long, StatusEntity> implements StatusDao {

	@Override
	public StatusEntity findById(long id) {
		return getByKey(id);
	}

}
