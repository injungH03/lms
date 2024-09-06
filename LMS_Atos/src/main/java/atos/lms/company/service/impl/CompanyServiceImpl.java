package atos.lms.company.service.impl;

import java.util.Arrays;
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
import atos.lms.company.service.CompanyExcelVO;
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
	
	  // 업체 목록 엑셀 다운로드 처리
    @Override
    public void companyListExcelDown(HttpServletResponse response, CompanyVO companyVO) throws Exception {
        List<CompanyExcelVO> companyList = companyDao.companyListExcelDown(companyVO);
        
        // 필드와 헤더 매핑 설정
        Map<String, String> fieldToHeaderMap = new LinkedHashMap<>();
        fieldToHeaderMap.put("bizRegNo", "사업자등록번호");
        fieldToHeaderMap.put("corpName", "업체명");
        fieldToHeaderMap.put("repName", "대표자명");
        fieldToHeaderMap.put("bizType", "업태");
        fieldToHeaderMap.put("bizItem", "종목");
        fieldToHeaderMap.put("phoneNo", "전화번호");
        fieldToHeaderMap.put("faxNo", "팩스번호");
        fieldToHeaderMap.put("taxInvoice", "세금계산서(이메일)");
        fieldToHeaderMap.put("empCount", "직원수");
        fieldToHeaderMap.put("trainCount", "교육인원수");
        fieldToHeaderMap.put("trainManager", "교육담당자명");
        fieldToHeaderMap.put("trainEmail", "교육담당자(이메일)");
        fieldToHeaderMap.put("trainPhone", "교육담당자 전화번호");
        fieldToHeaderMap.put("taxManager", "세금계산서담당자명");
        fieldToHeaderMap.put("taxEmail", "세금계산서담당자(이메일)");
        fieldToHeaderMap.put("taxPhone", "세금계산서담당자(전화번호)");
        fieldToHeaderMap.put("regDate", "등록일");
        fieldToHeaderMap.put("zipcode", "우편번호");
        fieldToHeaderMap.put("addr1", "주소");
        fieldToHeaderMap.put("addr2", "상세주소");
        
        // 엑셀 파일로 출력
        ExcelUtil.exportToExcel(response, companyList, "업체목록", "업체목록엑셀파일", fieldToHeaderMap);
    }


}
