package atos.lms.education.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import atos.lms.common.utl.ExcelUtil;
import atos.lms.education.service.EducationExcelVO;
import atos.lms.education.service.EducationMasterVO;
import atos.lms.education.service.EducationService;
import atos.lms.education.service.EducationVO;

@Service("EducationService")
public class EducationServiceImpl extends EgovAbstractServiceImpl implements EducationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EducationServiceImpl.class);
	
	
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
    public void educationListExcelDown(HttpServletResponse response, EducationVO educationVO) throws Exception {
        List<EducationExcelVO> educationList = educationDAO.educationListExcelDown(educationVO);

        // 필드와 엑셀 헤더 매핑
        Map<String, String> fieldToHeaderMap = new LinkedHashMap<>();
        fieldToHeaderMap.put("title", "교육명");
        fieldToHeaderMap.put("category", "교육 분류");
        fieldToHeaderMap.put("description", "과정 소개");
        fieldToHeaderMap.put("objective", "과정 목표");
        fieldToHeaderMap.put("completionCriteria", "수료 조건");
        fieldToHeaderMap.put("note", "비고");
        fieldToHeaderMap.put("status", "상태");
        fieldToHeaderMap.put("trainingTime", "교육 시간");
        fieldToHeaderMap.put("regDate", "등록일");
        fieldToHeaderMap.put("register", "등록자");

        // 엑셀 파일로 출력
        ExcelUtil.exportToExcel(response, educationList, "교육목록", "교육목록엑셀파일", fieldToHeaderMap);
    }
    
    
    
    @Override
    public EducationVO selectEducationDetail(int eduCode) {
        return educationDAO.selectEducationDetail(eduCode);
    }
    
    
    @Override
    public void updateEducation(EducationVO educationVO) {
        // 로그 확인용 (선택 사항)
        System.out.println("수정할 교육 과정 정보: " + educationVO.toString());

        // DAO 호출하여 교육 과정 업데이트
        educationDAO.updateEducation(educationVO);
    }
    
    
    @Override
    public void deleteEducation(int eduCode) {
        try {
            // DAO에서 교육 상태를 '1005(삭제)'로 변경하는 메서드 호출
            educationDAO.deleteEducationByEduCode(eduCode);

            // 관련된 강의 상태를 '4002(폐강)'으로 변경하는 메서드 호출
            educationDAO.deleteLecturesByEduCode(eduCode);

            LOGGER.info("교육 과정과 관련된 강의가 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            LOGGER.error("교육 과정 삭제 중 오류 발생", e);
            throw e;
        }
    }

    
}
