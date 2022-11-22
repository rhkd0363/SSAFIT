package com.ssafit.pjt.model.service;


import java.util.HashMap;
import java.util.List;

import com.ssafit.pjt.model.dto.Board;

public interface BoardService {
	
	void writeBoard(Board board);
	
	List<Board> getBoardList(HashMap<String, String> params);
	
	Board getBoard(int id);
	
	boolean modifyBoard(Board board);
	
	boolean removeBoard(int id);
	
	void updateViewCnt(int id);
}
