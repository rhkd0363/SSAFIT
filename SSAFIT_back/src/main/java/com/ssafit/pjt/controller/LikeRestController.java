package com.ssafit.pjt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.pjt.model.dto.Video;
import com.ssafit.pjt.model.service.LikeService;

@RestController
@RequestMapping("/api")
public class LikeRestController {
	
	@Autowired
	LikeService likeService;
	
	
	@GetMapping("like")
	public ResponseEntity<List<Video>> showLikeList(String user_id){
		return new ResponseEntity<List<Video>>(likeService.showLikeList(user_id),HttpStatus.OK);
	}
	
	@PostMapping("like")
	public ResponseEntity<String> registLike(String video_id, String user_id){
		if(likeService.registLike(video_id, user_id) == 1)
			return new ResponseEntity<String>("success",HttpStatus.CREATED);
		return new ResponseEntity<String>("fail",HttpStatus.OK);
	}
	
	@DeleteMapping("like")
	public ResponseEntity<String> removeLike(String video_id, String user_id){
		if(likeService.removeLike(video_id, user_id) == 1)
			return new ResponseEntity<String>("success",HttpStatus.OK);
		return new ResponseEntity<String>("fail",HttpStatus.OK);
	}
	
	
	
}
