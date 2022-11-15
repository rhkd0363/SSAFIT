package com.ssafit.pjt.model.dao;

import java.util.List;

import com.ssafit.pjt.model.dto.User;
import com.ssafit.pjt.model.dto.Video;

public interface LikeDao {
	//좋아요 추가
	int insertLike(String video_id,String user_id);
	
	
	//좋아요 삭제
	int deleteLike(String video_id,String user_id);
	
	//좋아요한 영상 목록 조회
	List<Video> selectLikeList(String user_id);
	
}
