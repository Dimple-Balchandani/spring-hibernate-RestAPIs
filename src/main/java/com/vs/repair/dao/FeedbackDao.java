package com.vs.repair.dao;

import java.util.List;

import com.vs.repair.model.FeedbackEntity;

public interface FeedbackDao {
	
	boolean addFeedback(FeedbackEntity request);
	
	void deleteFeedback(FeedbackEntity request);
	
	List<FeedbackEntity> list();
	
	void updateFeedback(long id, FeedbackEntity request);

	FeedbackEntity findById(long id);
}
