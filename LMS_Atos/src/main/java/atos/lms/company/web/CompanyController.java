package atos.lms.company.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import atos.lms.common.utl.ResponseVO;
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
		System.out.println("companyList resultList: " + map.get("resultList"));
		System.out.println("companyList resultCnt: " + map.get("resultCnt"));

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

		System.out.println("companyList resultList: " + map.get("resultList"));

		return "company/companyList";
	}

	// 등록 페이지 이동
	@RequestMapping("/company/CompanyRegistView.do")
	public String companyRegistView(@ModelAttribute("companySearchVO") CompanyVO companyVO, ModelMap model)
			throws Exception {

		List<CompanyMasterVO> company = companyService.selectCompany();
		model.addAttribute("company", company);

		return "company/companyRegist"; // 등록 페이지의 JSP 파일 경로에 맞게 수정 필요
	}

	
	@RequestMapping("/company/companyInsert")
	@ResponseBody
	public ResponseEntity<ResponseVO> companyInsert(@RequestBody CompanyVO companyVO) {
	    LOGGER.info("Entering companyInsert method with data: {}", companyVO);

	    try {
	        if (companyService.isBizRegNoDuplicate(companyVO.getBizRegNo())) {
	            LOGGER.warn("Duplicate business registration number detected: {}", companyVO.getBizRegNo());

	            ResponseVO responseVO = new ResponseVO();
	            responseVO.setHttpStatus(HttpStatus.CONFLICT);
	            responseVO.setMessage("이미 존재하는 사업자등록번호입니다.");
	            return ResponseEntity.status(responseVO.getHttpStatus()).body(responseVO);
	        }

	        companyService.insertCompany(companyVO);

	        LOGGER.info("Company insertion successful for: {}", companyVO.getBizRegNo());
	        ResponseVO responseVO = new ResponseVO();
	        responseVO.setHttpStatus(HttpStatus.OK);
	        responseVO.setMessage("등록 완료");
	        return ResponseEntity.status(responseVO.getHttpStatus()).body(responseVO);

	    } catch (Exception e) {
	        LOGGER.error("Error occurred during company insertion", e);
	        ResponseVO responseVO = new ResponseVO();
	        responseVO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
	        responseVO.setMessage("서버 오류가 발생했습니다.");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseVO);
	    }
	}

	
	
	
	@RequestMapping("/company/checkDuplicateBizRegNo")
	@ResponseBody
	public ResponseEntity<Map<String, Boolean>> checkDuplicateBizRegNo(@RequestBody Map<String, String> request) throws Exception {
	    String bizRegNo = request.get("bizRegNo");
	    boolean isDuplicate = companyService.isBizRegNoDuplicate(bizRegNo);

	    Map<String, Boolean> response = new HashMap<>();
	    response.put("duplicate", isDuplicate);

	    return ResponseEntity.ok(response);
	}
	
	
	@RequestMapping("/company/companyDetail.do")
	public String companyDetail(@ModelAttribute("companySearchVO") CompanyVO companyVO, ModelMap model) throws Exception {
	    // bizRegNo를 통해 서비스에서 상세 정보 조회
	    CompanyVO companyDetail = companyService.selectCompanyDetail(companyVO.getBizRegNo());

	    // 조회한 데이터를 모델에 추가하여 JSP에서 사용 가능하게 설정
	    model.addAttribute("company", companyDetail);

	    // 상세 조회 페이지로 이동
	    return "company/companyDetail"; // 상세 조회 JSP 경로에 맞게 수정 필요
	}
	
	
	@RequestMapping("/company/companyUpdateView.do")
	public String companyUpdateView(@ModelAttribute("companySearchVO") CompanyVO companyVO, ModelMap model) throws Exception {
	    // 회사 정보를 조회하여 모델에 추가
	    CompanyVO companyDetail = companyService.selectCompanyDetail(companyVO.getBizRegNo());
	    model.addAttribute("company", companyDetail);
	    return "company/companyUpdate"; // 수정 페이지의 JSP 파일 경로
	}
	
	
	@RequestMapping("/company/companyUpdate")
	@ResponseBody
	public ResponseEntity<ResponseVO> companyUpdate(@RequestBody CompanyVO companyVO) throws Exception {
	    
	    LOGGER.info("Received company update request for: {}", companyVO);

	    // 회사 정보 업데이트 수행
	    companyService.updateCompany(companyVO);

	    ResponseVO responseVO = new ResponseVO();
	    responseVO.setHttpStatus(HttpStatus.OK);
	    responseVO.setMessage("수정 완료");

	    LOGGER.info("Company update successful for: {}", companyVO.getBizRegNo());
	    
	    return ResponseEntity.status(responseVO.getHttpStatus()).body(responseVO);
	}
	
	
	@RequestMapping("/company/deleteCompany")
	@ResponseBody
	public ResponseEntity<ResponseVO> deleteCompany(@RequestBody CompanyVO companyVO) throws Exception {
	    
	    String bizRegNo = companyVO.getBizRegNo();  // 회사 정보에서 사업자등록번호 추출
	    
	    // 회사와 관련된 회원 모두 삭제 상태로 변경
	    companyService.deleteCompanyAndMembers(bizRegNo);
	    
	    ResponseVO responseVO = new ResponseVO();
	    responseVO.setHttpStatus(HttpStatus.OK);
	    responseVO.setMessage("삭제 완료");
	    
	    return ResponseEntity.status(responseVO.getHttpStatus()).body(responseVO);
	}
	
	@RequestMapping("/company/updateStatus")
	@ResponseBody
	public ResponseEntity<ResponseVO> updateCompanyStatus(@RequestBody CompanyVO companyVO) throws Exception {
	    
	    String bizRegNo = companyVO.getBizRegNo();  // 회사 정보에서 사업자등록번호 추출
	    String status = companyVO.getStatus();  // 변경할 상태 값 추출
	    
	    // 회사와 관련된 회원 상태를 포함하여 상태 변경
	    companyService.updateStatus(bizRegNo, status);
	    
	    ResponseVO responseVO = new ResponseVO();
	    responseVO.setHttpStatus(HttpStatus.OK);
	    responseVO.setMessage("상태 변경 완료");
	    
	    return ResponseEntity.status(responseVO.getHttpStatus()).body(responseVO);
	}

    // 엑셀 다운로드 요청 처리
    @RequestMapping("/company/companyListExcelDown")
    public void companyListExcelDown(HttpServletResponse response, CompanyVO companyVO) throws Exception {
        companyService.companyListExcelDown(response, companyVO);
    }
	

}
