package com.ssafit.pjt.model.dao;

import java.util.List;

import com.ssafit.pjt.model.dto.User;
import com.ssafit.pjt.model.dto.Video;

public interface FollowDao {
	
	List<String> selectFollowing(String user_id);
	
	List<String> selectFollower(String user_id);
	
	int insertFollow(String user_id, String follow_id);
	
	int deleteFollow(String user_id, String follow_id);
	
}
