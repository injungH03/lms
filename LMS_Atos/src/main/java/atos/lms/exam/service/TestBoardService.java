package atos.lms.exam.service;

import java.util.Map;

public interface TestBoardService {
	
	Map<String, Object> selectBoardList(TestBoardVO testBoardVO);
	
	void insert(TestBoardVO testBoardVO);
	
	void update(TestBoardVO testBoardVO);
	
	TestBoardVO selectBoardKey(TestBoardVO testBoardVO);
	
	
	

}
