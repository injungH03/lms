package atos.lms.lecture.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface LectureService {
	
	Map<String, Object> selectLecture(LectureVO lectureVO);
	
	Map<String, Object> selectEducation(LectureVO lectureVO);
	
	Map<String, Object> selectInstructor(LectureInsDTO lectureInsDTO);
	
	LectureVO selectLectureKey(LectureVO lectureVO);
	
	LectureVO selectLectureTitle(LectureEnrollDTO lectureEnrollDTO);
	
	Map<String, Object> selectEnrollList(LectureEnrollDTO lectureEnrollDTO);
	
	Map<String, Object> selectStudentList(LectureMemDTO lectureMemDTO);
	
	Map<String, Object> saveInstructor(LectureInsDTO lectureInsDTO);
	
	Map<String, Object> saveLecture(LectureVO lectureVO);
	
	Map<String, Object> deleteLecture(LectureVO lectureVO);
	
	Map<String, Object> deleteStudent(LectureMemDTO lectureMemDTO);
	
	Map<String, Object> insertStudent(LectureMemDTO lectureMemDTO);
	
	Map<String, Object> insertSelectedStudents(LectureMemDTO lectureMemDTO);
	
	

}
