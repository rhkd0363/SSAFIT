package com.ssafit.pjt.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.pjt.model.dao.FollowDao;

@Service
public class FollowServiceImpl implements FollowService{

	@Autowired
	FollowDao followDao;
	
	@Override
	public List<String> showFollowing(String user_id) {
		return followDao.selectFollowing(user_id);
	}

	@Override
	public List<String> showFollower(String user_id) {
		return followDao.selectFollower(user_id);
	}

	@Override
	public int registFollow(String user_id, String follow_id) {
		return followDao.insertFollow(user_id, follow_id);
	}

	@Override
	public int removeFollow(String user_id, String follow_id) {
		return followDao.deleteFollow(user_id, follow_id);
	}
	

}
