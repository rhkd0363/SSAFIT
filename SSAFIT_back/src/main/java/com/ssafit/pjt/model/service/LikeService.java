package com.ssafit.pjt.model.service;

import java.util.List;

import com.ssafit.pjt.model.dto.Video;

public interface LikeService {
	//좋아요 추가
	int registLike(String video_id,String user_id);
	
	//좋아요 삭제
	int removeLike(String video_id,String user_id);
	
	//좋아요한 영상 목록 조회
	List<Video> showLikeList(String user_id);
	
}
