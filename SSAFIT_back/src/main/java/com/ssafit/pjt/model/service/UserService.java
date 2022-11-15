package com.ssafit.pjt.model.service;

import java.util.List;

import com.ssafit.pjt.model.dto.User;

public interface UserService {
	int registUser(User user);
	
	User loginUser(String id, String pw);
	
	int modifyUser(User user);
	
	List<User> showList();
	
	Boolean checkUser(String user_id);
}
