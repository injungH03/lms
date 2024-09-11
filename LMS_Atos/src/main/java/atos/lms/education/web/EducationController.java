package atos.lms.education.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import atos.lms.common.utl.ResponseVO;
import atos.lms.education.service.EducationMasterVO;
import atos.lms.education.service.EducationService;
import atos.lms.education.service.EducationVO; 


@Controller
public class EducationController {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(EducationController.class);

    @Resource(name = "EducationService")
    private EducationService educationService;

    @RequestMapping("/education/educationList.do")
    public String educationList(@ModelAttribute("educationSearchVO") EducationVO educationVO, ModelMap model) throws Exception {

    	
        // 페이지네이션 설정
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(educationVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(educationVO.getPageUnit());
        paginationInfo.setPageSize(educationVO.getPageSize());

        educationVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        educationVO.setLastIndex(paginationInfo.getLastRecordIndex());
        educationVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        
        System.out.println("검색 조건: " + educationVO.getSearchCnd());
        System.out.println("검색어: " + educationVO.getSearchWrd());
        System.out.println("상태 코드: " + educationVO.getStatusCode());
        System.out.println("페이지 인덱스: " + educationVO.getPageIndex());
        
        // 교육 목록 조회
        Map<String, Object> map = educationService.selectEducationList(educationVO);
        
		// 데이터 출력 부분
		System.out.println("educationList resultList: " + map.get("resultList"));
		System.out.println("educationList resultCnt: " + map.get("resultCnt"));
		
        int totalCount = Integer.parseInt(String.valueOf(map.get("resultCnt")));
    
        paginationInfo.setTotalRecordCount(totalCount);
        
        List<EducationMasterVO> status = educationService.selectStatusCode();
        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("status", status);

        return "education/educationList";
    }
    
    
    // 상태 업데이트 요청을 처리하는 메서드
    @RequestMapping("/education/updateStatus")
    @ResponseBody
    public ResponseEntity<ResponseVO> updateStatus(@RequestBody Map<String, Object> requestData) throws Exception {
        List<Integer> eduCodes = (List<Integer>) requestData.get("eduCodes");  // 여러 개의 교육 코드 가져오기
        String status = (String) requestData.get("status");  // 변경할 상태 값 가져오기

        // 상태 업데이트 서비스 호출
        educationService.updateStatus(eduCodes, status);

        // 상태 업데이트 성공 메시지 반환
        ResponseVO responseVO = new ResponseVO();
        responseVO.setHttpStatus(HttpStatus.OK);
        responseVO.setMessage("상태 변경 완료");

        return ResponseEntity.status(responseVO.getHttpStatus()).body(responseVO);
    }
    
    
    
    // 등록 페이지 이동
    @RequestMapping("/education/educationRegistView.do")
    public String educationRegistView(@ModelAttribute("educationVO") EducationVO educationVO, ModelMap model) throws Exception {
    	
        // 로그 추가
        System.out.println("educationRegistView 접근");
    	
    	
        // 분류 및 수료 조건 데이터 조회
        List<EducationVO> mainCategories = educationService.selectMainCategories();
        List<EducationMasterVO> completionCriteria = educationService.selectCompletionCriteria();
        
        // 로그 추가
        System.out.println("mainCategories: " + mainCategories);
        
        for (EducationMasterVO criteria : completionCriteria) {
            System.out.println("수료 조건 코드: " + criteria.getCompletionCode() + ", 진도율: " + criteria.getCompletionRate() + 
                               ", 시험 점수: " + criteria.getCompletionScore() + ", 설문 유무: " + criteria.getCompletionSurvey());
        }
        // 모델에 데이터 추가
        model.addAttribute("mainCategories", mainCategories);
        model.addAttribute("completionCriteria", completionCriteria);

        return "education/educationRegist"; // JSP 파일 경로에 맞게 수정
    }
    
    
    // 교육 과정 등록 처리
    @RequestMapping("/education/educationInsert")
    @ResponseBody
    public ResponseEntity<ResponseVO> educationInsert(@RequestBody EducationVO educationVO) throws Exception {
        // 교육 과정 등록 처리
        educationService.insertEducation(educationVO);

        // 성공 메시지 반환
        ResponseVO responseVO = new ResponseVO();
        responseVO.setHttpStatus(HttpStatus.OK);
        responseVO.setMessage("교육 과정이 성공적으로 등록되었습니다.");

        return ResponseEntity.status(responseVO.getHttpStatus()).body(responseVO);
    }

    
    
    @RequestMapping("/education/mainCategories")
    @ResponseBody
    public List<EducationVO> getMainCategories() {
        return educationService.selectMainCategories();
    }

    @RequestMapping("/education/subCategories")
    @ResponseBody
    public List<EducationVO> getSubCategories(@RequestParam("mainCode") String mainCode) {
    	System.out.println("Received mainCode: " + mainCode);  // mainCode 값 출력
        return educationService.selectSubCategories(mainCode);
    }

    @RequestMapping("/education/detailCategories")
    @ResponseBody
    public List<EducationVO> getDetailCategories(@RequestParam("subCode") String subCode) {
    	System.out.println("Received subCode: " + subCode);  // mainCode 값 출력
        return educationService.selectDetailCategories(subCode);
    }
    
  
    
}
