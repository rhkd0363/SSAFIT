package com.ssafit.pjt.model.service;

import java.util.List;

import com.ssafit.pjt.model.dto.User;

public interface FollowService {
	
	List<User> showFollowing(String search_name, String user_id);
	
	List<User> showFollower(String search_name, String user_id);
	
	int registFollow(String user_id, String follow_id);
	
	int removeFollow(String user_id, String follow_id);
	
	int followingCnt(String user_id);
	
	int followerCnt(String user_id);
}
