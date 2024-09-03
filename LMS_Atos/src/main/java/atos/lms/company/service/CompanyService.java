package atos.lms.company.service;

import java.util.List;
import java.util.Map;

public interface CompanyService {

	Map<String, Object> selectCompanyList(CompanyVO companyVO);

	List<CompanyMasterVO> selectStatusCode();

	List<CompanyMasterVO> selectCompany();
	
    // 업체 등록 메서드 추가
    void insertCompany(CompanyVO companyVO);
    
    // 사업자등록번호 중복검사
    boolean isBizRegNoDuplicate(String bizRegNo);

}
