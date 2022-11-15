package com.ssafit.pjt.model.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafit.pjt.model.dto.Video;

@Repository
public interface VideoDao {
	
	int insertVideo(Video video);
	
	List<Video> selectVideoList(HashMap<String, String> params);
	
	Video selectVideo(String video_id);
	
	int updateViewCnt(String video_id);
}
