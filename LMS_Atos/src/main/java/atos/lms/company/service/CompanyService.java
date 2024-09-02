package atos.lms.company.service;

import java.util.List;
import java.util.Map;

public interface CompanyService {

	Map<String, Object> selectCompanyList(CompanyVO companyVO);

	List<CompanyMasterVO> selectStatusCode();

	List<CompanyMasterVO> selectCompany();
	

}
