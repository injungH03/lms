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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("CompanyService")
public class CompanyServiceImpl extends EgovAbstractServiceImpl implements CompanyService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);

	@Resource(name = "CompanyDAO")
	private CompanyDAO companyDao;

	@Override
	public Map<String, Object> selectCompanyList(CompanyVO companyVO) {
		LOGGER.info("selectCompanyList called with criteria: {}", companyVO);

		Map<String, Object> resultMap = new HashMap<>();
		try {
			resultMap.put("resultList", companyDao.selectCompanyList(companyVO));
			resultMap.put("resultCnt", companyDao.selectCompanyListCnt(companyVO));
		} catch (Exception e) {
			LOGGER.error("Error fetching company list", e);
		}

		LOGGER.info("selectCompanyList completed with result count: {}", resultMap.get("resultCnt"));
		return resultMap;
	}

	@Override
	public List<CompanyMasterVO> selectStatusCode() {
		return companyDao.selectStatusCode();
	}

	@Override
	public List<CompanyMasterVO> selectCompany() {
		return companyDao.selectCompany();
	}

	@Override
	public void insertCompany(CompanyVO companyVO) {
		LOGGER.info("Inserting company: {}", companyVO);
		try {
			companyDao.insertCompany(companyVO);
			LOGGER.info("Company inserted successfully: {}", companyVO.getBizRegNo());
		} catch (Exception e) {
			LOGGER.error("Error inserting company", e);
			throw e;
		}
	}

	@Override
	public boolean isBizRegNoDuplicate(String bizRegNo) {
		int count = companyDao.checkDuplicateBizRegNo(bizRegNo);
		return count > 0;
	}
	
	
	@Override
	public CompanyVO selectCompanyDetail(String bizRegNo) {
	    // DAO를 통해 업체 상세 정보 가져오기
	    return companyDao.selectCompanyDetail(bizRegNo);
	}

}
