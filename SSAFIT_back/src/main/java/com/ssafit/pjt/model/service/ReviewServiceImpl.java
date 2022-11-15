package com.ssafit.pjt.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.pjt.model.dao.ReviewDao;
import com.ssafit.pjt.model.dto.Reply;
import com.ssafit.pjt.model.dto.Review;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	ReviewDao reviewDao;
	
	@Override
	public List<Review> showReviewList(String video_id) {
		return reviewDao.selectReviewList(video_id);
	}

	@Override
	public int registReview(Review review) {
		return reviewDao.insertReview(review);
	}

	@Override
	public int removeReview(int review_id) {
		return reviewDao.deleteReview(review_id);
	}

	@Override
	public int modifyReview(Review review) {
		return reviewDao.updateReview(review);
	}

	@Override
	public List<Reply> showReplyList(int review_id) {
		return reviewDao.selectReplyList(review_id);
	}

	@Override
	public int registReply(Reply reply) {
		return reviewDao.insertReply(reply);
	}

	@Override
	public int removeReply(int reply_id) {
		return reviewDao.deleteReply(reply_id);
	}

	@Override
	public int modifyReply(Reply reply) {
		return reviewDao.updateReply(reply);
	}

}
