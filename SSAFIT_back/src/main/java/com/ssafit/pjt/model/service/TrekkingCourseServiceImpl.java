package com.ssafit.pjt.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.pjt.model.dao.TrekkingCourseDao;
import com.ssafit.pjt.model.dto.TrekkingCourse;

@Service
public class TrekkingCourseServiceImpl implements TrekkingCourseService {

	@Autowired
	TrekkingCourseDao trekkingCourseDao;

	@Override
	public List<TrekkingCourse> getCourseList(String searchKeyword, String difficulty, String distance) {
		return trekkingCourseDao.selectCourseList(searchKeyword, difficulty, distance);
	}

}
