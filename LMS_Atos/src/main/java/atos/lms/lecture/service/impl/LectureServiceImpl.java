package atos.lms.lecture.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import atos.lms.common.sort.SortFieldValidator;
import atos.lms.lecture.service.LectureInsDTO;
import atos.lms.lecture.service.LectureService;
import atos.lms.lecture.service.LectureVO;
import atos.lms.lecture.service.dao.LectureDAO;

@Service
public class LectureServiceImpl implements LectureService {

	@Autowired
	LectureDAO lectureDao;
	
	@Autowired
    private SortFieldValidator sortFieldValidator;
	
	
	
	@Override
	public Map<String, Object> selectLecture(LectureVO lectureVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		
//		System.out.println("넘어온 정렬 필드 = " + lectureVO.getSortField());
//		System.out.println("넘어온 정렬 조건 = " + lectureVO.getSortOrder());
		
		// SQL인젝션방지
		String validateField = sortFieldValidator.validateSortField("lecture", lectureVO.getSortField());
		String validateOrder = sortFieldValidator.validateSortOrder(lectureVO.getSortOrder());
		
//		System.out.println("정렬 필드 = " + validateField);
//		System.out.println("정렬 조건 = " + validateOrder);
		
		// 검증된 값 입력
		lectureVO.setSortField(validateField);
		lectureVO.setSortOrder(validateOrder);
		
		//교육방식
		lectureVO.setLectureMethod("집체");
		
		map.put("resultList", lectureDao.selectLectureList(lectureVO));
		map.put("resultCnt", lectureDao.selectLectureListCnt(lectureVO));
		
		return map;
	}

	@Override
	public Map<String, Object> selectEducation(LectureVO lectureVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// SQL인젝션방지
		String validateField = sortFieldValidator.validateSortField("education", lectureVO.getSortField());
		String validateOrder = sortFieldValidator.validateSortOrder(lectureVO.getSortOrder());
		
		// 검증된 값 입력
		lectureVO.setSortField(validateField);
		lectureVO.setSortOrder(validateOrder);
		
		map.put("resultList", lectureDao.selectEducationList(lectureVO));
		map.put("resultCnt", lectureDao.selectEducationListCnt(lectureVO));
		
		return map;
	}

	@Override
	public Map<String, Object> selectInstructor(LectureInsDTO lectureInsDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		map.put("resultList", lectureDao.selectInstructorList(lectureInsDTO));
		
		return map;
	}

	@Override
	public Map<String, Object> saveInstructor(LectureInsDTO lectureInsDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 강의의 배정된 강사를 수정 
		lectureDao.updateLectureInstructor(lectureInsDTO);
		
		if ("C".equals(lectureInsDTO.getType())) {
			lectureDao.insertInstructor(lectureInsDTO);
			map.put("message", "강사 배정 완료");
			
		} else if ("U".equals(lectureInsDTO.getType())) {
			lectureDao.updateInstructor(lectureInsDTO);
			map.put("message", "강사 배정 수정 완료");
		}
		
		return map;
	}

	@Override
	public Map<String, Object> saveLecture(LectureVO lectureVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if ("C".equals(lectureVO.getType())) {
			lectureDao.insertLecture(lectureVO);
			map.put("message", "강의 개설 완료");
		} else if ("U".equals(lectureVO.getType())) {
			lectureDao.updateLecture(lectureVO);
			map.put("message", "강의 수정 완료");
		}
		
		return map;
	}

	@Override
	public LectureVO selectLectureKey(LectureVO lectureVO) {
		return lectureDao.selectLectureKey(lectureVO);
	}

}
