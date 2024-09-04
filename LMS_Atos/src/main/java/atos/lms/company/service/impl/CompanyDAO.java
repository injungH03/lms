package atos.lms.company.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import atos.lms.company.service.CompanyMasterVO;
import atos.lms.company.service.CompanyVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository("CompanyDAO")
public class CompanyDAO extends EgovComAbstractDAO {

	public List<CompanyVO> selectCompanyList(CompanyVO companyVO) {
		return selectList("company.selectCompanyList", companyVO);
	}

	public int selectCompanyListCnt(CompanyVO companyVO) {
		return (Integer) selectOne("company.selectCompanyListCnt", companyVO);
	}
	
	
	public List<CompanyMasterVO> selectStatusCode() {
		return selectList("company.selectStatusCode");
	}

	public List<CompanyMasterVO> selectCompany() {
		return selectList("company.selectCompany");
	}
	
    // 업체 등록 메서드 추가
    public void insertCompany(CompanyVO companyVO) {
        insert("company.insertCompany", companyVO);
    }
    
    public int checkDuplicateBizRegNo(String bizRegNo) {
        return (Integer) selectOne("company.checkDuplicateBizRegNo", bizRegNo);
    }
    
    public CompanyVO selectCompanyDetail(String bizRegNo) {
        return selectOne("company.selectCompanyDetail", bizRegNo);
    }
    
    

}
