package atos.lms.company.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import atos.lms.company.service.CompanyMasterVO;
import atos.lms.company.service.CompanyService;
import atos.lms.company.service.CompanyVO;

@Service("CompanyService")
public class CompanyServiceImpl extends EgovAbstractServiceImpl implements CompanyService {

	
	@Resource(name = "CompanyDAO")
	private CompanyDAO companyDao;
	
	
	@Override
	public Map<String, Object> selectCompanyList(CompanyVO companyVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		map.put("resultList", companyDao.selectCompanyList(companyVO));
		map.put("resultCnt", companyDao.selectCompanyListCnt(companyVO));
		
		return map;
	}
	
	
	@Override
	public List<CompanyMasterVO> selectStatusCode() {
		return companyDao.selectStatusCode();
	}

	@Override
	public List<CompanyMasterVO> selectCompany() {
		return companyDao.selectCompany();
	}

	
}
