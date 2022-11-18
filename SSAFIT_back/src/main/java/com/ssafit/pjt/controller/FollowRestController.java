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

import com.ssafit.pjt.model.dto.User;
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
	
	@GetMapping("followLikeVideoList")
	public ResponseEntity<List<Video>> followLike(String user_id){
		return new ResponseEntity<List<Video>>(followInfoService.showFollowLikeVideo(user_id),HttpStatus.OK);
	}
	
	@GetMapping("followingList")
	public ResponseEntity<List<User>> following(String search_name, String user_id){
		return new ResponseEntity<List<User>>(followService.showFollowing(search_name, user_id),HttpStatus.OK);
	}
	
	@GetMapping("followerList")
	public ResponseEntity<List<User>> follower(String search_name,String user_id){
		return new ResponseEntity<List<User>>(followService.showFollower(search_name, user_id),HttpStatus.OK);
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
	
	@GetMapping("followingCnt")
	public ResponseEntity<Integer> followingCnt(String user_id){
		return new ResponseEntity<Integer>(followService.followingCnt(user_id),HttpStatus.OK);
	}
	
	@GetMapping("followerCnt")
	public ResponseEntity<Integer> followerCnt(String user_id){
		return new ResponseEntity<Integer>(followService.followerCnt(user_id),HttpStatus.OK);
	}
	
	
	
}
