package atos.lms.education.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public interface EducationService {
	
    Map<String, Object> selectEducationList(EducationVO educationVO);

    List<EducationMasterVO> selectStatusCode();

    void updateStatus(List<Integer> eduCodes, String status);

	void insertEducation(EducationVO educationVO);	
    
    List<EducationMasterVO> selectCompletionCriteria();  // 수료 조건 데이터 조회

    List<EducationVO> selectAllCategoryList();
    
    List<Map<String, Object>> selectTrainingTimeList();
    
    void educationListExcelDown(HttpServletResponse response, EducationVO educationVO) throws Exception;
    
    EducationVO selectEducationDetail(int eduCode);
    
    void updateEducation(EducationVO educationVO);

}
