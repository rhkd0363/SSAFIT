package com.ssafit.pjt.model.service;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.ssafit.pjt.model.dto.Video;

public interface VideoService {
	
	int registVideo(String search) throws IOException;
	
	List<Video> showVideoList(HashMap<String,String> params);
	
	Video showVideo(String video_id);
}
