package com.ssafit.pjt.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.pjt.model.dao.LikeDao;
import com.ssafit.pjt.model.dto.Video;

@Service
public class LikeServiceImpl implements LikeService{

	@Autowired
	LikeDao likeDao;
	
	
	@Override
	public int registLike(String video_id, String user_id) {
		return likeDao.insertLike(video_id, user_id);
	}

	@Override
	public int removeLike(String video_id, String user_id) {
		return likeDao.deleteLike(video_id, user_id);
	}

	@Override
	public List<Video> showLikeList(String user_id) {
		return likeDao.selectLikeList(user_id);
	}

	@Override
	public int showLike(String video_id, String user_id) {
		return likeDao.selectLike(video_id, user_id);
	}

}
