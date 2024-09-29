package atos.lms.lecture.service.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import atos.lms.lecture.service.LectureVO;


@Repository
public interface LectureDAO  {
	
	List<LectureVO> selectLectureList(LectureVO lectureVO);
	
	int selectLectureListCnt(LectureVO lectureVO);

}
