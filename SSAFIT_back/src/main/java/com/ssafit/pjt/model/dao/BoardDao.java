package com.ssafit.pjt.model.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafit.pjt.model.dto.Board;

@Repository
public interface BoardDao {
	
	List<Board> selectList(HashMap<String, String> params);
	
	int insertBoard(Board board);
	
	int updateBoard(Board board);
	
	int deleteBoard(int id);
	
	Board selectOne(int id);
}
