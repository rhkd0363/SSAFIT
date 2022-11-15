package com.ssafit.pjt.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.pjt.model.dto.Video;
import com.ssafit.pjt.model.service.VideoService;

@RestController
@RequestMapping("/api")
public class VideoRestController {
	
	@Autowired
	VideoService videoService;
	
	
	@GetMapping("video")
	public ResponseEntity<List<Video>> showList(String keyword, String body_part){
		HashMap<String, String> params = new HashMap<String, String>();
		
		params.put("keyword", keyword);
		params.put("body_part", body_part);
		
		
		return new ResponseEntity<List<Video>>(videoService.showVideoList(params),HttpStatus.OK); 
	}

	
	@GetMapping("video/{id}")
	public ResponseEntity<Video> showVideo(@PathVariable String video_id){
		return new ResponseEntity<Video>(videoService.showVideo(video_id),HttpStatus.OK);
	}
	
	/**
	 * DB 유튜브 데이터 추가 용도
	 * @return
	 * @throws IOException
	 */
	@PutMapping("video/youtube/insert")
	public ResponseEntity<String> regist() throws IOException{
		videoService.registVideo("복부운동");
		return new ResponseEntity<String>(HttpStatus.OK); 
	}
	
}
