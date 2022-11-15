package com.ssafit.pjt.model.dto;

public class Review {
	private String video_id;
	private String user_id;
	private String review_content;
	private int review_id;
	private String reg_date;
	
	public Review() {
		// TODO Auto-generated constructor stub
	}

	public String getVideo_id() {
		return video_id;
	}

	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getReview_content() {
		return review_content;
	}

	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}

	public int getReview_id() {
		return review_id;
	}

	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public Review(String video_id, String user_id, String review_content, int review_id, String reg_date) {
		super();
		this.video_id = video_id;
		this.user_id = user_id;
		this.review_content = review_content;
		this.review_id = review_id;
		this.reg_date = reg_date;
	}
	
	
}
