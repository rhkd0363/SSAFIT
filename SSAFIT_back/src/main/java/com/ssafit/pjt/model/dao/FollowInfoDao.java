package com.ssafit.pjt.model.dao;

import java.util.List;

import com.ssafit.pjt.model.dto.User;
import com.ssafit.pjt.model.dto.Video;

public interface FollowInfoDao {
	
	//내가 팔로잉 중인 사람들이 좋아요한 영상 목록 조회
	List<Video> selectFollowLikeVideo(String user_id);
	
}
