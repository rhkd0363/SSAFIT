package com.ssafit.pjt.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafit.pjt.model.dto.User;

@Repository
public interface UserDao {
	//로그인(마이페이지 정보조회)
	User selectUser(String user_id, String user_pw);
	
	//회원 목록
	List<User> selectList();
	
	//회원 가입
	int insertUser(User user);
	
	//회원정보 수정
	int updateUser(User user);
	
	// 아이디 중복체크
	User checkUser(String user_id);
}
