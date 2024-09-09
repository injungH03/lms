package atos.lms.education.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import atos.lms.company.service.impl.CompanyServiceImpl;
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
    public List<EducationMasterVO> selectCompany() {
        return educationDAO.selectCompany();
    }
}
