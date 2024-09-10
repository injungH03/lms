package atos.lms.education.service;

import java.util.List;
import java.util.Map;

public interface EducationService {
	
    Map<String, Object> selectEducationList(EducationVO educationVO);

    List<EducationMasterVO> selectStatusCode();

    void updateStatus(int eduCode, String status);

}
