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
    
    // 교육 시간 목록 조회 메서드 추가
    public List<Map<String, Object>> selectTrainingTimeList() {
        return selectList("education.selectTrainingTimeList");
    }
    
    
    public List<EducationExcelVO> educationListExcelDown(EducationVO educationVO) {
        return selectList("education.selectEducationListForExcel", educationVO);
    }
    
    
    public EducationVO selectEducationDetail(int eduCode) {
        return selectOne("education.selectEducationDetail", eduCode);
    }

}