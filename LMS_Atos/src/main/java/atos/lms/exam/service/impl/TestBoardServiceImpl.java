package atos.lms.exam.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import atos.lms.exam.service.TestBoardService;
import atos.lms.exam.service.TestBoardVO;

@Service("TestBoardService")
public class TestBoardServiceImpl extends EgovAbstractServiceImpl implements TestBoardService {

	@Resource(name = "TestBoardDAO")
	private TestBoardDAO testBoardDao;

	@Override
	public Map<String, Object> selectBoardList(TestBoardVO testBoardVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", testBoardDao.selectBoardList(testBoardVO));
		map.put("resultCnt", testBoardDao.selectBoardListCnt(testBoardVO));
		
		return map;
	}

	@Override
	public void insert(TestBoardVO testBoardVO) {
		testBoardDao.insertBoard(testBoardVO);
	}

	@Override
	public TestBoardVO selectBoardKey(TestBoardVO testBoardVO) {
		return testBoardDao.selectBoardKey(testBoardVO);
	}

	@Override
	public void update(TestBoardVO testBoardVO) {
		testBoardDao.updateBoard(testBoardVO);
	}
	
	
	
	
}
