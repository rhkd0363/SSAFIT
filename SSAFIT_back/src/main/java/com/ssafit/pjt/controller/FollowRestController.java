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
import com.ssafit.pjt.model.service.FollowInfoService;
import com.ssafit.pjt.model.service.FollowService;

@RestController
@RequestMapping("/api")
public class FollowRestController {
	
	@Autowired
	FollowService followService;
	
	@Autowired
	FollowInfoService followInfoService;
	
	@GetMapping("followLike")
	public ResponseEntity<List<Video>> followLike(String user_id){
		return new ResponseEntity<List<Video>>(followInfoService.showFollowLikeVideo(user_id),HttpStatus.OK);
	}
	
	@GetMapping("following")
	public ResponseEntity<List<String>> following(String user_id){
		return new ResponseEntity<List<String>>(followService.showFollowing(user_id),HttpStatus.OK);
	}
	
	@GetMapping("follower")
	public ResponseEntity<List<String>> follower(String user_id){
		return new ResponseEntity<List<String>>(followService.showFollower(user_id),HttpStatus.OK);
	}
	
	@PostMapping("follow")
	public ResponseEntity<String> regist(String user_id,String follow_id){
		if(followService.registFollow(user_id,follow_id) == 1)
			return new ResponseEntity<String>("success",HttpStatus.OK);
		return new ResponseEntity<String>("fail",HttpStatus.OK);
	}
	
	@DeleteMapping("follow")
	public ResponseEntity<String> remove(String user_id, String follow_id){
		if(followService.removeFollow(user_id,follow_id) == 1)
			return new ResponseEntity<String>("success",HttpStatus.OK);
		return new ResponseEntity<String>("fail",HttpStatus.OK);
	}
	
	
}
