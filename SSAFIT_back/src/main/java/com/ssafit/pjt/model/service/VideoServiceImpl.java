package com.ssafit.pjt.model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.pjt.model.dao.VideoDao;
import com.ssafit.pjt.model.dto.Video;

@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	VideoDao videoDao;

	@Override
	public List<Video> showVideoList(HashMap<String, String> params) {
		return videoDao.selectVideoList(params);
	}
	
	@Override
	public Video showVideo(String video_id) {
		videoDao.updateViewCnt(video_id);
		return videoDao.selectVideo(video_id);
	}

	/**
	 * 유튜브 동영상DB 추가 용도
	 */
	@Override
	public int registVideo(String search) throws IOException {
		
		String apiurl = "https://www.googleapis.com/youtube/v3/search";
		
		String nextPageToken = null;
		
		for (int i = 0; i < 6; i++) {
			URL url;
			if(nextPageToken != null) {
				String tmpUrl = apiurl + "&pageToken=" + URLEncoder.encode(nextPageToken,"UTF-8");
				url = new URL(tmpUrl);
			}else {
				url = new URL(apiurl);
			}
			
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			
			org.json.JSONObject youtubeJSON = new org.json.JSONObject(response.toString());
			
			nextPageToken = youtubeJSON.getString("nextPageToken");
			
			System.out.println(nextPageToken);
			
			org.json.JSONArray items = youtubeJSON.getJSONArray("items");
			
			int num = 1;
			
			for (Object item : items) {
				org.json.JSONObject itemDtl = (org.json.JSONObject) item;
				
				Video video = new Video();
				
				video.setVideo_id(itemDtl.getJSONObject("id").getString("videoId"));
				video.setVideo_title(itemDtl.getJSONObject("snippet").getString("title"));
				video.setThumbnail_url(itemDtl.getJSONObject("snippet").getJSONObject("thumbnails")
						.getJSONObject("high").getString("url"));
				video.setChannel_title(itemDtl.getJSONObject("snippet").getString("channelTitle"));
				video.setChannel_id(itemDtl.getJSONObject("snippet").getString("channelId"));
				video.setBody_part("복부");
				
				System.out.println(num++);
				
				if (videoDao.selectVideo(video.getVideo_id()) == null) {
					videoDao.insertVideo(video);
				}
			}
			
		}
		
		return 1;
	}

	@Override
	public void updateViewCnt(String video_id) {
		videoDao.updateViewCnt(video_id);
	}

}

