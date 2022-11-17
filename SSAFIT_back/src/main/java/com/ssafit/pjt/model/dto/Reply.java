package com.ssafit.pjt.model.dto;

public class Reply {
	private int reply_id;
	private int review_id;
	private String reply_content;
	private String reg_date;
	private String user_id;
	private String user_name;
	
	public Reply() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Reply [reply_id=" + reply_id + ", review_id=" + review_id + ", reply_content=" + reply_content
				+ ", reg_date=" + reg_date + ", user_id=" + user_id + ", user_name=" + user_name + "]";
	}
	public Reply(int reply_id, int review_id, String reply_content, String reg_date, String user_id, String user_name) {
		super();
		this.reply_id = reply_id;
		this.review_id = review_id;
		this.reply_content = reply_content;
		this.reg_date = reg_date;
		this.user_id = user_id;
		this.user_name = user_name;
	}
	public int getReply_id() {
		return reply_id;
	}
	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}
	public int getReview_id() {
		return review_id;
	}
	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	
	
}
