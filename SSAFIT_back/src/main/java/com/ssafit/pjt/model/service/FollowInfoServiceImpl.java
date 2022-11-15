package com.ssafit.pjt.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssafit.pjt.model.dao.FollowInfoDao;
import com.ssafit.pjt.model.dto.Video;

public class FollowInfoServiceImpl implements FollowInfoService{

	@Autowired
	FollowInfoDao followInfoDao;
	
	@Override
	public List<Video> showFollowLikeVideo(String user_id) {
		return followInfoDao.selectFollowLikeVideo(user_id);
	}

}
