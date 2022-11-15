package com.ssafit.pjt.model.dao;

import java.util.List;

import com.ssafit.pjt.model.dto.Reply;
import com.ssafit.pjt.model.dto.Review;

public interface ReviewDao {
	
	//리뷰 가져오기
	List<Review> selectReviewList(String video_id);
	
	//리뷰 등록
	int insertReview(Review review);
	
	//리뷰 삭제
	int deleteReview(int review_id);
	
	//리뷰 수정
	int updateReview(Review review);
	
	//리뷰 대댓 가져오기
	List<Reply> selectReplyList(int review_id);
		
	//리뷰 대댓 등록
	int insertReply(Reply reply);
	
	//리뷰 대댓 삭제
	int deleteReply(int reply_id);
	
	//리뷰 대댓 수정
	int updateReply(Reply reply);
}
