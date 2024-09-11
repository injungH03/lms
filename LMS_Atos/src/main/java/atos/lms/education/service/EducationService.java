package atos.lms.education.service;

import java.util.List;
import java.util.Map;

public interface EducationService {
	
    Map<String, Object> selectEducationList(EducationVO educationVO);

    List<EducationMasterVO> selectStatusCode();

    void updateStatus(List<Integer> eduCodes, String status);

	void insertEducation(EducationVO educationVO);	
    
    List<EducationMasterVO> selectCompletionCriteria();  // 수료 조건 데이터 조회

    // 대분류 조회
    List<EducationVO> selectMainCategories();

    // 중분류 조회
    List<EducationVO> selectSubCategories(String mainCode);

    // 소분류 조회
    List<EducationVO> selectDetailCategories(String subCode);
    
}
