package atos.lms.lecture.service.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import atos.lms.lecture.service.LectureEnrollDTO;
import atos.lms.lecture.service.LectureInsDTO;
import atos.lms.lecture.service.LectureMemDTO;
import atos.lms.lecture.service.LectureVO;


@Repository
public interface LectureDAO  {
	
	List<LectureVO> selectLectureList(LectureVO lectureVO);
	
	int selectLectureListCnt(LectureVO lectureVO);

	List<LectureVO> selectEducationList(LectureVO lectureVO);
	
	int selectEducationListCnt(LectureVO lectureVO);
	
	List<LectureInsDTO> selectInstructorList(LectureInsDTO lectureInsDTO);
	
	List<LectureEnrollDTO> selectEnrollList(LectureEnrollDTO lectureEnrollDTO);
	
	int selectEnrollListCnt(LectureEnrollDTO lectureEnrollDTO);
	
	LectureVO selectLectureKey(LectureVO lectureVO);
	
	LectureVO selectLectureOne(LectureEnrollDTO lectureEnrollDTO);
	
	List<LectureMemDTO> selectStudentList(LectureMemDTO lectureMemDTO);
	
	int selectStudentListCnt(LectureMemDTO lectureMemDTO);
	
	List<LectureMemDTO> selectCompanyList();
	
	void insertLecture(LectureVO lectureVO);
	
	void updateLecture(LectureVO lectureVO);
	
	/** 강의에 강사 업데이트*/
	void updateLectureInstructor(LectureInsDTO lectureInsDTO);
	
	/** 강사 배정 */
	void insertInstructor(LectureInsDTO lectureInsDTO);
	
	/** 강사 배정 */
	void updateInstructor(LectureInsDTO lectureInsDTO);
	
	/** 강사 배정 */
	void deleteInstructor(LectureInsDTO lectureInsDTO);
	
	/** 강의 수정시 배정 강사 삭제*/
	int deleteLectureInstructor(LectureVO lectureVO);
	
	/** 강의 삭제 실제 업데이트 수행 상태값 변경 */
	void deleteLecture(LectureVO lectureVO);
	
	/** 배정된 수강생 배정 취소 (삭제) */
	void deleteStudent(LectureMemDTO lectureMemDTO);
	
	/** 수강생 배정 */
	void insertStudent(LectureMemDTO lectureMemDTO);
	
	/** 수강생 배정시 출석 테이블에 수강생 추가 */
	void insertAttendance(LectureMemDTO lectureMemDTO);
	
	/** 수강생 배정시 수료 테이블에 수강생 추가 */
	void insertCertification(LectureMemDTO lectureMemDTO);
	
	/** 수강생 N건 배정 PK 조회 */
	List<Integer> selectEnrollIds(LectureMemDTO lectureMemDTO);
	
	void insertStudentBatch(LectureMemDTO lectureMemDTO);
	
	void insertAttendanceBatch(LectureMemDTO lectureMemDTO);
	
	void insertCertificationBatch(LectureMemDTO lectureMemDTO);
	
}
