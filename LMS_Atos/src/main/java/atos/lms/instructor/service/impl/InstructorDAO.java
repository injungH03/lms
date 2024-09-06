package atos.lms.instructor.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import atos.lms.instructor.service.InstructorMasterVO;
import atos.lms.instructor.service.InstructorVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository("InstructorDAO")
public class InstructorDAO extends EgovComAbstractDAO {
	
	public List<InstructorMasterVO> selectStatusCode() {
		return selectList("instructor.selectStatusCode");
	}
	
	public List<InstructorVO> selectInstructorList(InstructorVO instructorVO) {
		return selectList("instructor.selectInstructorList", instructorVO);
	}
	
	public int selectInstructorListCnt(InstructorVO instructorVO) {
		return (Integer) selectOne("instructor.selectInstructorListCnt", instructorVO);
	}
	
	public List<InstructorVO> selectInstructorListExcel(InstructorVO instructorVO) {
		return selectList("instructor.selectInstructorListExcel", instructorVO);
	}
	
	public void updateInstructorStatus(InstructorMasterVO instructorMasterVO) {
		update("instructor.updateInstructorStatus", instructorMasterVO);
	}
	
	public void insertinstructor(InstructorVO instructorVO) {
		insert("instructor.insertInstructor", instructorVO);
	}
	
	public void deleteInstructor(InstructorVO instructorVO) {
		update("instructor.deleteInstructor", instructorVO);
	}
	
	public void updateInstructor(InstructorVO instructorVO) {
		update("instructor.updateInstructor", instructorVO);
	}
	
	public InstructorVO selectInstructorKey(InstructorVO instructorVO) {
		return selectOne("instructor.selectInstructorKey", instructorVO);
	}
}
