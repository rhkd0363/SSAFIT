package com.ssafit.pjt.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafit.pjt.model.dto.TrekkingCourse;

@Repository
public interface TrekkingCourseDao {
	
	List<TrekkingCourse> selectCourseList(String searchKeyword,String difficulty, String distance);
}
