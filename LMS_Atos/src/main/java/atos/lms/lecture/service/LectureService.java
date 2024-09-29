package atos.lms.lecture.service;

import java.util.Map;

public interface LectureService {
	
	Map<String, Object> selectLecture(LectureVO lectureVO);

}
