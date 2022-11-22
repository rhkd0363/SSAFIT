package com.ssafit.pjt.model.dto;

public class Board {
	private int id;
	private String title;
	private String user_id;
	private String user_name;
	private String user_img;
	private String content;
	private String reg_date;
	private int view_cnt;

	public Board() {
	}

	public Board(int id, String title, String user_id, String user_name, String user_img, String content,
			String reg_date, int view_cnt) {
		super();
		this.id = id;
		this.title = title;
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_img = user_img;
		this.content = content;
		this.reg_date = reg_date;
		this.view_cnt = view_cnt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getUser_img() {
		return user_img;
	}

	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public int getView_cnt() {
		return view_cnt;
	}

	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}

	
}
