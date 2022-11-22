package com.ssafit.pjt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.pjt.model.dto.TrekkingCourse;
import com.ssafit.pjt.model.service.TrekkingCourseService;

@RestController
@RequestMapping("/api")
public class TrekkingCourseRestController {

	private static final String SUCCESS = "succes";
	private static final String FAIL = "fail";

	@Autowired
	private TrekkingCourseService trekkingCourseService;

	@GetMapping("/trekking")
	public ResponseEntity<List<TrekkingCourse>> list(String searchKeyword, String difficulty, String distance){
		return new ResponseEntity<List<TrekkingCourse>>(trekkingCourseService.getCourseList(searchKeyword, difficulty, distance),HttpStatus.OK);
	}

}
