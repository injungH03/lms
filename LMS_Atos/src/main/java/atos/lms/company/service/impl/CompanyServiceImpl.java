package atos.lms.company.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import atos.lms.company.service.CompanyMasterVO;
import atos.lms.company.service.CompanyService;
import atos.lms.company.service.CompanyVO;

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
	
	@Override
	public void updateCompany(CompanyVO companyVO) {
	    LOGGER.info("Updating company: {}", companyVO);
	    try {
	        companyDao.updateCompany(companyVO);
	        LOGGER.info("Company updated successfully: {}", companyVO.getBizRegNo());
	    } catch (Exception e) {
	        LOGGER.error("Error updating company", e);
	        throw e;
	    }
	}

	
	@Override
	public void deleteCompanyAndMembers(String bizRegNo) {
	    LOGGER.info("Deleting company and related members for bizRegNo: {}", bizRegNo);
	    try {
	        // 회사의 상태를 '삭제'로 변경
	        companyDao.deleteCompany(bizRegNo);
	        
	        // 해당 회사와 관련된 모든 회원의 상태를 '삭제'로 변경
	        companyDao.deleteMembersByCompany(bizRegNo);
	        
	        LOGGER.info("Company and related members deleted successfully for bizRegNo: {}", bizRegNo);
	    } catch (Exception e) {
	        LOGGER.error("Error deleting company and members", e);
	        throw e; // 예외를 다시 던져서 상위 레이어에 알림
	    }
	}
	
	@Override
	public void updateStatus(String bizRegNo, String status) {
	    LOGGER.info("Updating company and related members status for bizRegNo: {}", bizRegNo);
	    
	    try {
	        // bizRegNo를 리스트로 분리
	        List<String> bizRegNoList = Arrays.asList(bizRegNo.split(","));

	        // 업체 상태 변경
	        CompanyMasterVO companyMasterVO = new CompanyMasterVO();
	        companyMasterVO.setCorpBizList(bizRegNoList);  // 리스트로 설정
	        companyMasterVO.setStatusCode(status);
	        companyDao.updateStatus(companyMasterVO);

	        // 해당 업체에 소속된 회원들의 상태도 함께 변경
	        companyDao.updateMembersByCompany(companyMasterVO);

	        LOGGER.info("Company and related members status updated successfully for bizRegNo: {}", bizRegNo);
	    } catch (Exception e) {
	        LOGGER.error("Error updating company and members status", e);
	        throw e;  // 예외를 던져서 상위 레이어에 알림
	    }
	}



}
