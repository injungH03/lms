package atos.lms.company.web;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import atos.lms.company.service.CompanyMasterVO;
import atos.lms.company.service.CompanyService;
import atos.lms.company.service.CompanyVO;


@Controller
public class CompanyController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);
	
	@Resource(name = "CompanyService")
	private CompanyService companyService;
	
	@RequestMapping("/company/companyList.do")
	public String companyList(@ModelAttribute("companySearchVO") CompanyVO companyVO, ModelMap model) throws Exception {

		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(companyVO.getPageIndex());
    	paginationInfo.setRecordCountPerPage(companyVO.getPageUnit());
    	paginationInfo.setPageSize(companyVO.getPageSize());
		
    	companyVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
    	companyVO.setLastIndex(paginationInfo.getLastRecordIndex());
    	companyVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
    	
    	Map<String, Object> map = companyService.selectCompanyList(companyVO);
		
        // 데이터 출력 부분
        System.out.println("resultList: " + map.get("resultList"));
        System.out.println("resultCnt: " + map.get("resultCnt"));
    	
    	
    	int totalcount = Integer.parseInt(String.valueOf(map.get("resultCnt")));
    	
    	paginationInfo.setTotalRecordCount(totalcount);
    	
    	
    	List<CompanyMasterVO> status = companyService.selectStatusCode();
    	List<CompanyMasterVO> company = companyService.selectCompany();
    	
        // 상태 코드와 회사 리스트 출력
        System.out.println("status: " + status);
        System.out.println("company: " + company);
    	
    	
    	model.addAttribute("resultList", map.get("resultList"));
    	model.addAttribute("paginationInfo", paginationInfo);
    	model.addAttribute("status", status);
    	model.addAttribute("company", company);
    	
    	System.out.println("resultList: " + map.get("resultList"));
    	
    	
		return "company/companyList";
	}

}
