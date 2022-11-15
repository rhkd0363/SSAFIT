package com.ssafit.pjt.model.service;

import java.util.List;

import com.ssafit.pjt.model.dto.Reply;
import com.ssafit.pjt.model.dto.Review;

public interface ReviewService {
		//리뷰 가져오기
		List<Review> showReviewList(String video_id);
		
		//리뷰 등록
		int registReview(Review review);
		
		//리뷰 삭제
		int removeReview(int review_id);
		
		//리뷰 수정
		int modifyReview(Review review);
		
		//리뷰 대댓 가져오기
		List<Reply> showReplyList(int review_id);
			
		//리뷰 대댓 등록
		int registReply(Reply reply);
		
		//리뷰 대댓 삭제
		int removeReply(int reply_id);
		
		//리뷰 대댓 수정
		int modifyReply(Reply reply);
}
