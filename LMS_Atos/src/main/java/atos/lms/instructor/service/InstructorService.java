package atos.lms.instructor.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;


public interface InstructorService {

	Map<String, Object> selectInstructorList(InstructorVO instructorVO);
	
	List<InstructorMasterVO> selectStatusCode();
	
	InstructorVO selectInstructorKey(InstructorVO instructorVO);
	
	void instructorListExcelDown(HttpServletResponse response, InstructorVO instructorVO) throws Exception;
	
	void updateInstructorStatus(String ids, String status);
	
	void instructorInsert(InstructorVO instructorVO);
	
	void instructorDelete(InstructorVO instructorVO);
	
	void updateInstructor(InstructorVO instructorVO);
}
