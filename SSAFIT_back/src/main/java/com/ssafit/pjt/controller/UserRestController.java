package com.ssafit.pjt.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.pjt.Util.JwtUtil;
import com.ssafit.pjt.model.dto.User;
import com.ssafit.pjt.model.service.UserService;

@RestController
@RequestMapping("/api")
public class UserRestController {

<<<<<<< HEAD
	private final String SUCCESS = "success";
	private final String FAIL = "fail";
=======
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
>>>>>>> 620815eb0d70ee1141fb037c91e61264f4edd1d3

	@Autowired
	UserService userService;

<<<<<<< HEAD
	@GetMapping("user/idcheck")
	public ResponseEntity<String> idCheck(String user_id) {
		if (userService.checkUser(user_id)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}

=======
	
>>>>>>> 620815eb0d70ee1141fb037c91e61264f4edd1d3
	/**
	 * 
	 * @param user_id
	 * @return 유저 아이디 중복 확인
	 */
	@GetMapping("user")
	public ResponseEntity<String> idCheck(String user_id) {
		if (userService.checkUser(user_id)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
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

<<<<<<< HEAD
=======
	@Autowired
	private JwtUtil jwtUtil;

>>>>>>> 620815eb0d70ee1141fb037c91e61264f4edd1d3
	/**
	 * 
	 * @param id 회원 ID
	 * @param pw 회원 PW
	 * @return 로그인 정보 일치 시 User 정보 아닐 시 null 값
	 */
	@GetMapping("user/login")
<<<<<<< HEAD
	public ResponseEntity<User> login(String user_id, String user_pw) {
		System.out.println(userService.loginUser(user_id, user_pw));
		return new ResponseEntity<User>(userService.loginUser(user_id, user_pw), HttpStatus.OK);
=======
	public ResponseEntity<Map<String, Object>> login(String user_id, String user_pw) {
		System.out.println(userService.loginUser(user_id, user_pw));

		HashMap<String, Object> result = new HashMap<String, Object>();
		HttpStatus status = null;

		User user = userService.loginUser(user_id, user_pw);

		try {
			if (user != null) {
				result.put("access-token", jwtUtil.createToken("id", user.getUser_id()));
				result.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				result.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (UnsupportedEncodingException e) {
			result.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;			
		}

		return new ResponseEntity<Map<String, Object>>(result, status);
>>>>>>> 620815eb0d70ee1141fb037c91e61264f4edd1d3
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
