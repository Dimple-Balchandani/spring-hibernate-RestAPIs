package com.vs.repair.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vs.repair.model.FeedbackEntity;

@Repository
public class FeedbackDaoImpl extends AbstractDao<Long, FeedbackEntity> implements FeedbackDao{
	
	public boolean addFeedback(FeedbackEntity Feedback) {
		try {
			persist(Feedback);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void deleteFeedback(FeedbackEntity feedback) {
		delete(feedback);
	}
	
	@SuppressWarnings("unchecked")
	public List<FeedbackEntity> list() {
		return entityManager.createQuery("SELECT u from FeedbackEntity u").getResultList();
	}

	public void updateFeedback(long id, FeedbackEntity feedback) {
		feedback.setId(id);
		update(feedback);
	}

	public FeedbackEntity findById(long id) {
		FeedbackEntity entity = getByKey(id);
		return entity;
		
	}
}
