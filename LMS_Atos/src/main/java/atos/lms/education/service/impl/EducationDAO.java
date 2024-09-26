package atos.lms.education.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import atos.lms.education.service.EducationExcelVO;
import atos.lms.education.service.EducationMasterVO;
import atos.lms.education.service.EducationVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository("EducationDAO")
public class EducationDAO extends EgovComAbstractDAO {
	
    public List<EducationVO> selectEducationList(EducationVO educationVO) {
        return selectList("education.selectEducationList", educationVO);
    }

    public int selectEducationListCnt(EducationVO educationVO) {
        return (Integer) selectOne("education.selectEducationListCnt", educationVO);
    }

    public List<EducationMasterVO> selectStatusCode() {
        return selectList("education.selectStatusCode");
    }

	
    public void updateStatus(EducationMasterVO educationMasterVO) {
        update("education.updateStatus", educationMasterVO);  
    }
    
    public void insertEducation(EducationVO educationVO) {
        insert("education.insertEducation", educationVO);
    }
    

    public List<EducationMasterVO> selectCompletionCriteria() {
        return selectList("education.selectCompletionCriteria");  // 수료 조건 조회 쿼리 호출
    }
    
    public List<EducationVO> selectAllCategoryList() {
        return selectList("education.selectAllCategoryList");
    }
    
    
    public List<EducationExcelVO> educationListExcelDown(EducationVO educationVO) {
        return selectList("education.selectEducationListForExcel", educationVO);
    }
    
    
    public EducationVO selectEducationDetail(int eduCode) {
        return selectOne("education.selectEducationDetail", eduCode);
    }
    
    public void updateEducation(EducationVO educationVO) {
        update("education.updateEducation", educationVO);
    }
    
    public void deleteEducationByEduCode(int eduCode) {
        update("education.deleteEducationByEduCode", eduCode);  // Mapper에서 작성한 쿼리 호출
    }

    // 관련된 강의 상태를 '4002(폐강)'으로 변경하는 쿼리
    public void deleteLecturesByEduCode(int eduCode) {
        update("education.deleteLecturesByEduCode", eduCode);  // Mapper에서 작성한 쿼리 호출
    }

}