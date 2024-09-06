package atos.lms.company.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import atos.lms.company.service.CompanyExcelVO;
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
    
    public void updateCompany(CompanyVO companyVO) {
        update("company.updateCompany", companyVO);
    }

    public void deleteCompany(String bizRegNo) {
        update("company.deleteCompany", bizRegNo);
    }

    public void deleteMembersByCompany(String bizRegNo) {
        update("company.deleteMembersByCompany", bizRegNo);
    }
    
    // 업체 상태 변경
    public void updateStatus(CompanyMasterVO companyMasterVO) {
        update("company.updateStatus", companyMasterVO);
    }

    // 회원 상태 변경
    public void updateMembersByCompany(CompanyMasterVO companyMasterVO) {
        update("company.updateMembersByCompany", companyMasterVO);
    }
    
    // 엑셀 다운로드용 업체 목록 조회 메서드 추가
    public List<CompanyExcelVO> companyListExcelDown(CompanyVO companyVO) {
        return selectList("company.selectCompanyListForExcel", companyVO);
    }

}
