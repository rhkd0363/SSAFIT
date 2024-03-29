package com.ssafit.pjt.model.dto;

public class User {
	private String user_name;
	private String user_id;
	private String user_pw;
	private String user_email;
	private String user_phone_number;
	private String user_img;
	private int ref_follow;
	private int followingCnt;
	private int followerCnt;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String user_name, String user_id, String user_pw, String user_email, String user_phone_number,
			String user_img, int ref_follow, int followingCnt, int followerCnt) {
		super();
		this.user_name = user_name;
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_email = user_email;
		this.user_phone_number = user_phone_number;
		this.user_img = user_img;
		this.ref_follow = ref_follow;
		this.followingCnt = followingCnt;
		this.followerCnt = followerCnt;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_phone_number() {
		return user_phone_number;
	}

	public void setUser_phone_number(String user_phone_number) {
		this.user_phone_number = user_phone_number;
	}

	public String getUser_img() {
		return user_img;
	}

	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}

	public int getRef_follow() {
		return ref_follow;
	}

	public void setRef_follow(int ref_follow) {
		this.ref_follow = ref_follow;
	}

	public int getFollowingCnt() {
		return followingCnt;
	}

	public void setFollowingCnt(int followingCnt) {
		this.followingCnt = followingCnt;
	}

	public int getFollowerCnt() {
		return followerCnt;
	}

	public void setFollowerCnt(int followerCnt) {
		this.followerCnt = followerCnt;
	}
	
}
