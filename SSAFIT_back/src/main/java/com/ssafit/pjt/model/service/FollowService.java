package com.ssafit.pjt.model.service;

import java.util.List;

public interface FollowService {
	
	List<String> showFollowing(String user_id);
	
	List<String> showFollower(String user_id);
	
	int registFollow(String user_id, String follow_id);
	
	int removeFollow(String user_id, String follow_id);
}
