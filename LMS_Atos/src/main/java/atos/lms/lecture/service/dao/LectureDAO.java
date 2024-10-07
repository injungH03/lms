package atos.lms.lecture.service.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import atos.lms.lecture.service.LectureInsDTO;
import atos.lms.lecture.service.LectureVO;


@Repository
public interface LectureDAO  {
	
	List<LectureVO> selectLectureList(LectureVO lectureVO);
	
	int selectLectureListCnt(LectureVO lectureVO);

	List<LectureVO> selectEducationList(LectureVO lectureVO);
	
	int selectEducationListCnt(LectureVO lectureVO);
	
	List<LectureInsDTO> selectInstructorList(LectureInsDTO lectureInsDTO);
	
	/** 강의에 강사 업데이트*/
	void updateLectureInstructor(LectureInsDTO lectureInsDTO);
	
	/** 강사 배정 */
	void insertInstructor(LectureInsDTO lectureInsDTO);
	
	/** 강사 배정 */
	void updateInstructor(LectureInsDTO lectureInsDTO);
	
	/** 강사 배정 */
	void deleteInstructor(LectureInsDTO lectureInsDTO);
	
	
}
