package atos.lms.education.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

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
    
    // 대분류 조회
    public List<EducationVO> selectMainCategories() {
        return selectList("education.selectMainCategories");
    }

    // 중분류 조회
    public List<EducationVO> selectSubCategories(String mainCode) {
        return selectList("education.selectSubCategories", mainCode);
    }

    // 소분류 조회
    public List<EducationVO> selectDetailCategories(String subCode) {
        return selectList("education.selectDetailCategories", subCode);
    }
    
    
    

}