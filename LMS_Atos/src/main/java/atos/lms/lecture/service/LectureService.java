package atos.lms.lecture.service;

import java.util.Map;

public interface LectureService {
	
	Map<String, Object> selectLecture(LectureVO lectureVO);
	
	Map<String, Object> selectEducation(LectureVO lectureVO);
	
	Map<String, Object> selectInstructor(LectureInsDTO lectureInsDTO);
	
	Map<String, Object> saveInstructor(LectureInsDTO lectureInsDTO);

}
