package atos.lms.lecture.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import atos.lms.lecture.service.LectureService;
import atos.lms.lecture.service.LectureVO;
import atos.lms.lecture.service.dao.LectureDAO;

@Service
public class LectureServiceImpl implements LectureService {

	@Autowired
	LectureDAO lectureDao;
	
	@Override
	public Map<String, Object> selectLecture(LectureVO lectureVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", lectureDao.selectLectureList(lectureVO));
		map.put("resultCnt", lectureDao.selectLectureListCnt(lectureVO));
		
		return map;
	}

}
