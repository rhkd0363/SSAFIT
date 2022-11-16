package com.ssafit.pjt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.pjt.model.dto.Reply;
import com.ssafit.pjt.model.dto.Review;
import com.ssafit.pjt.model.service.ReviewService;

@RestController
@RequestMapping("/api")
public class ReviewRestController {
	
	@Autowired
	ReviewService reviewService;
	
	
	@GetMapping("review")
	public ResponseEntity<List<Review>> showReviewList(String video_id){
		return new ResponseEntity<List<Review>>(reviewService.showReviewList(video_id),HttpStatus.OK);
	}
	
	@PostMapping("review")
	public ResponseEntity<String> registReview(Review review){
		if(reviewService.registReview(review) == 1)
			return new ResponseEntity<String>("success",HttpStatus.CREATED);
		return new ResponseEntity<String>("fail",HttpStatus.OK);
	}
	
	@DeleteMapping("review")
	public ResponseEntity<String> removeReview(int review_id){
		if(reviewService.removeReview(review_id) == 1)
			return new ResponseEntity<String>("success",HttpStatus.OK);
		return new ResponseEntity<String>("fail",HttpStatus.OK);
	}
	
	@PutMapping("review")
	public ResponseEntity<String> modifyReview(Review review){
		if(reviewService.modifyReview(review) == 1)
			return new ResponseEntity<String>("success",HttpStatus.OK);
		return new ResponseEntity<String>("fail",HttpStatus.OK);
	}
	
	@GetMapping("reply")
	public ResponseEntity<List<Reply>> showReplyList(int review_id){
		return new ResponseEntity<List<Reply>>(reviewService.showReplyList(review_id),HttpStatus.OK);
	}
	
	@PostMapping("reply")
	public ResponseEntity<String> registReply(Reply reply){
		if(reviewService.registReply(reply) == 1)
			return new ResponseEntity<String>("success",HttpStatus.CREATED);
		return new ResponseEntity<String>("fail",HttpStatus.OK);
	}
	
	@DeleteMapping("reply")
	public ResponseEntity<String> removeReply(int reply_id){
		if(reviewService.removeReply(reply_id) == 1)
			return new ResponseEntity<String>("success",HttpStatus.OK);
		return new ResponseEntity<String>("fail",HttpStatus.OK);
	}
	
	@PutMapping("reply")
	public ResponseEntity<String> modifyReply(Reply reply){
		if(reviewService.modifyReply(reply) == 1)
			return new ResponseEntity<String>("success",HttpStatus.OK);
		return new ResponseEntity<String>("fail",HttpStatus.OK);
	}
	
}
