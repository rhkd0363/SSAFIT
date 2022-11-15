package com.ssafit.pjt.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.pjt.model.dao.UserDao;
import com.ssafit.pjt.model.dto.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
	public int registUser(User user) {
		return userDao.insertUser(user);
	}

	@Override
	public User loginUser(String id, String pw) {
		return userDao.selectUser(id, pw);
	}

	@Override
	public int modifyUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public List<User> showList() {
		return userDao.selectList();
	}

	@Override
	public Boolean checkUser(String user_id) {
		if(userDao.checkUser(user_id) != null) {
			return false;			
		}
		return true;
	}
	
	
}
