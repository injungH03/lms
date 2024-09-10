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

}