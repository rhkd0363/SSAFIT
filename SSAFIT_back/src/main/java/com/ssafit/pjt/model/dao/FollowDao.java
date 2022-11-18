package com.ssafit.pjt.model.dao;

import java.util.List;

import com.ssafit.pjt.model.dto.User;

public interface FollowDao {
	
	List<User> selectFollowing(String search_name, String user_id);
	
	List<User> selectFollower(String search_name, String user_id);
	
	int insertFollow(String user_id, String follow_id);
	
	int deleteFollow(String user_id, String follow_id);
	
	int followingCnt(String user_id);
	
	int followerCnt(String user_id);
}
