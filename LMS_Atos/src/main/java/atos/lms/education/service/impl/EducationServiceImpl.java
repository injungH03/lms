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
        // 정상 상태 코드 설정 및 로깅 추가
        educationVO.setStatus("1002");
        System.out.println("설정된 상태값: " + educationVO.getStatus());

        // 카테고리 값 확인 및 설정
        if (educationVO.getCategory() == null || educationVO.getCategory().isEmpty()) {
            // 만약 category 값이 null 또는 빈 문자열이라면, mainCode를 기본값으로 설정
            if (educationVO.getMainCode() != null && !educationVO.getMainCode().isEmpty()) {
                educationVO.setCategory(educationVO.getMainCode());
                System.out.println("카테고리 값이 없으므로 mainCode로 설정: " + educationVO.getMainCode());
            }
        }

        // educationVO에 설정된 값들을 확인
        System.out.println("교육 과정 등록: " + educationVO.toString());

        // DAO 호출을 통해 데이터 삽입
        educationDAO.insertEducation(educationVO);
    }
    
   
    @Override
    public List<EducationMasterVO> selectCompletionCriteria() {
        return educationDAO.selectCompletionCriteria();  // 수료 조건 데이터 조회
    }
    
    @Override
    public List<EducationVO> selectAllCategoryList() {
        return educationDAO.selectAllCategoryList();
    }
    
    @Override
    public List<Map<String, Object>> selectTrainingTimeList() {
        return educationDAO.selectTrainingTimeList();
    }
    
    
}
