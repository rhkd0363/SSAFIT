package com.ssafit.pjt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.pjt.model.dto.User;
import com.ssafit.pjt.model.service.UserService;

@RestController
@RequestMapping("/api")
public class UserRestController {

	private final String SUCCESS = "success";
	private final String FAIL = "fail";

	@Autowired
	UserService userService;

	@GetMapping("user/idcheck")
	public ResponseEntity<String> idCheck(String user_id) {
		if (userService.checkUser(user_id)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}

	/**
	 * 
	 * @param user
	 * @return 성공 실패 여부
	 */
	@PostMapping("user")
	public ResponseEntity<String> regist(User user) {
		if (userService.checkUser(user.getUser_id())) {
			if (userService.registUser(user) == 1)
				return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id 회원 ID
	 * @param pw 회원 PW
	 * @return 로그인 정보 일치 시 User 정보 아닐 시 null 값
	 */
	@GetMapping("user/login")
	public ResponseEntity<User> login(String user_id, String user_pw) {
		System.out.println(userService.loginUser(user_id, user_pw));
		return new ResponseEntity<User>(userService.loginUser(user_id, user_pw), HttpStatus.OK);
	}

	/**
	 * 
	 * @param user
	 * @return 수정 성공 실패 여부
	 */
	@PutMapping("user")
	public ResponseEntity<String> modify(User user) {
		if (userService.modifyUser(user) == 1)
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}

	@GetMapping("user")
	public ResponseEntity<List<User>> showList() {
		return new ResponseEntity<List<User>>(userService.showList(), HttpStatus.OK);
	}
}
