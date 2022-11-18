package com.ssafit.pjt.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.pjt.model.dao.FollowDao;
import com.ssafit.pjt.model.dto.User;

@Service
public class FollowServiceImpl implements FollowService{

	@Autowired
	FollowDao followDao;
	
	@Override
	public List<User> showFollowing(String search_name, String user_id) {
		return followDao.selectFollowing(search_name, user_id);
	}

	@Override
	public List<User> showFollower(String search_name, String user_id) {
		return followDao.selectFollower(search_name, user_id);
	}

	@Override
	public int registFollow(String user_id, String follow_id) {
		return followDao.insertFollow(user_id, follow_id);
	}

	@Override
	public int removeFollow(String user_id, String follow_id) {
		return followDao.deleteFollow(user_id, follow_id);
	}

	@Override
	public int followingCnt(String user_id) {
		return followDao.followingCnt(user_id);
	}

	@Override
	public int followerCnt(String user_id) {
		return followDao.followerCnt(user_id);
	}
	

}
