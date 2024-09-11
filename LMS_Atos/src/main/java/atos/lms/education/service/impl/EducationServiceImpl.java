package atos.lms.education.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import atos.lms.education.service.EducationMasterVO;
import atos.lms.education.service.EducationService;
import atos.lms.education.service.EducationVO;

@Service("EducationService")
public class EducationServiceImpl extends EgovAbstractServiceImpl implements EducationService {
	
	
    @Resource(name = "EducationDAO")
    private EducationDAO educationDAO;

    @Override
    public Map<String, Object> selectEducationList(EducationVO educationVO) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultList", educationDAO.selectEducationList(educationVO));
        resultMap.put("resultCnt", educationDAO.selectEducationListCnt(educationVO));
        return resultMap;
    }

    @Override
    public List<EducationMasterVO> selectStatusCode() {
        return educationDAO.selectStatusCode();
    }

    @Override
    public void updateStatus(List<Integer> eduCodes, String status) {
        EducationMasterVO educationMasterVO = new EducationMasterVO();
        educationMasterVO.setEduCodeList(eduCodes);  // 여러 교육 코드를 리스트로 설정
        educationMasterVO.setStatusCode(status);  // 상태 코드 설정

        // DAO 메서드를 호출하여 상태를 업데이트
        educationDAO.updateStatus(educationMasterVO);
    }

    @Override
    public void insertEducation(EducationVO educationVO) {
        educationVO.setStatus("1002");  // 정상 상태로 설정
        educationDAO.insertEducation(educationVO);
    }
    
   
    @Override
    public List<EducationMasterVO> selectCompletionCriteria() {
        return educationDAO.selectCompletionCriteria();  // 수료 조건 데이터 조회
    }
    
    @Override
    public List<EducationVO> selectMainCategories() {
        return educationDAO.selectMainCategories();
    }

    @Override
    public List<EducationVO> selectSubCategories(String mainCode) {
        return educationDAO.selectSubCategories(mainCode);
    }

    @Override
    public List<EducationVO> selectDetailCategories(String subCode) {
        return educationDAO.selectDetailCategories(subCode);
    }
    

}
