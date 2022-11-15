package com.ssafit.pjt.model.dto;

public class Video {
	private String video_id;
	private String video_title;
	private String channel_title;
	private String channel_id;
	private String thumbnail_url;
	private int view_cnt;
	private String body_part;
	
	public Video() {
		// TODO Auto-generated constructor stub
	}

	public Video(String video_id, String video_title, String channel_title, String channel_id, String thumbnail_url,
			int view_cnt, String body_part) {
		super();
		this.video_id = video_id;
		this.video_title = video_title;
		this.channel_title = channel_title;
		this.channel_id = channel_id;
		this.thumbnail_url = thumbnail_url;
		this.view_cnt = view_cnt;
		this.body_part = body_part;
	}

	public String getVideo_id() {
		return video_id;
	}

	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}

	public String getVideo_title() {
		return video_title;
	}

	public void setVideo_title(String video_title) {
		this.video_title = video_title;
	}

	public String getChannel_title() {
		return channel_title;
	}

	public void setChannel_title(String channel_title) {
		this.channel_title = channel_title;
	}

	public String getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}

	public String getThumbnail_url() {
		return thumbnail_url;
	}

	public void setThumbnail_url(String thumbnail_url) {
		this.thumbnail_url = thumbnail_url;
	}

	public int getView_cnt() {
		return view_cnt;
	}

	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}

	public String getBody_part() {
		return body_part;
	}

	public void setBody_part(String body_part) {
		this.body_part = body_part;
	}

	@Override
	public String toString() {
		return "Video [video_id=" + video_id + ", video_title=" + video_title + ", channel_title=" + channel_title
				+ ", channel_id=" + channel_id + ", thumbnail_url=" + thumbnail_url + ", view_cnt=" + view_cnt
				+ ", body_part=" + body_part + "]";
	}

	
	
	
}
