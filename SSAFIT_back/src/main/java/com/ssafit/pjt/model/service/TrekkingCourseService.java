package com.ssafit.pjt.model.service;


import java.util.List;

import com.ssafit.pjt.model.dto.TrekkingCourse;

public interface TrekkingCourseService {
	
	List<TrekkingCourse> getCourseList(String searchKeyword,String difficulty, String distance);
	
}
