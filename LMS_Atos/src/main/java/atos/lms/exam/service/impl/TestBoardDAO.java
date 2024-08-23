package atos.lms.exam.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import atos.lms.exam.service.TestBoardVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository("TestBoardDAO")
public class TestBoardDAO extends EgovComAbstractDAO {
	
	public List<TestBoardVO> selectBoardList(TestBoardVO testBoardVO) {
		return selectList("testboard.selectBoardList", testBoardVO);
	}
	
	public int selectBoardListCnt(TestBoardVO testBoardVO) {
		return (Integer)selectOne("testboard.selectBoardListCnt", testBoardVO);
	}
	
	public void insertBoard(TestBoardVO testBoardVO) {
		insert("testboard.insertBoard", testBoardVO);
	}
	
	public TestBoardVO selectBoardKey(TestBoardVO testBoardVO) {
		return selectOne("testboard.selectBoardKey", testBoardVO);
	}
	
	public void updateBoard(TestBoardVO testBoardVO) {
		update("testboard.updateBoard", testBoardVO);
	}

}
