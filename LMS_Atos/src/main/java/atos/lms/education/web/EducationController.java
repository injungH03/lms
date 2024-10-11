package atos.lms.education.web;

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
        
        // 검색어 처리
        if (educationVO.getSearchWrd() != null) {
            String searchWrd = educationVO.getSearchWrd().trim();
            educationVO.setSearchWrd(searchWrd);
        }
        
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
        List<EducationVO> categories = educationService.selectAllCategoryList();
        List<EducationMasterVO> completionCriteria = educationService.selectCompletionCriteria();


       
        for (EducationMasterVO criteria : completionCriteria) {
            System.out.println("수료 조건 코드: " + criteria.getCompletionCode() + ", 진도율: " + criteria.getCompletionRate() + 
                               ", 시험 점수: " + criteria.getCompletionScore() + ", 설문 유무: " + criteria.getCompletionSurvey());
        }
        // 모델에 데이터 추가
        model.addAttribute("categories", categories);
        model.addAttribute("completionCriteria", completionCriteria);
        
        System.out.println("categories" + categories);
        
        
        return "education/educationRegist"; // JSP 파일 경로에 맞게 수정
    }
    

    
    @RequestMapping("/education/educationInsert")
    @ResponseBody
    public ResponseEntity<ResponseVO> educationInsert(@RequestBody EducationVO educationVO) {
        System.out.println("입력된 데이터: " + educationVO.toString());
        System.out.println("카테고리 코드: " + educationVO.getCategory());
        
        // 교육 과정 등록 처리
        educationService.insertEducation(educationVO);

        ResponseVO responseVO = new ResponseVO();
        responseVO.setHttpStatus(HttpStatus.OK);
        responseVO.setMessage("교육 과정이 성공적으로 등록되었습니다.");
        
        return ResponseEntity.status(responseVO.getHttpStatus()).body(responseVO);
    }
    
    // 엑셀 다운로드 요청 처리
    @RequestMapping("/education/educationListExcelDown")
    public void educationListExcelDown(HttpServletResponse response, EducationVO educationVO) throws Exception {
        educationService.educationListExcelDown(response, educationVO);
    }
    
    
    @RequestMapping("/education/educationDetail.do")
    public String educationDetail(@ModelAttribute("educationSearchVO") EducationVO educationVO, ModelMap model) throws Exception {
        LOGGER.info("요청으로 받은 eduCode: {}", educationVO.getEduCode());  // eduCode 출력

        // eduCode를 통해 서비스에서 상세 정보 조회
        EducationVO educationDetail = educationService.selectEducationDetail(educationVO.getEduCode());

        if (educationDetail == null) {
            LOGGER.warn("해당 eduCode로 조회된 데이터가 없습니다: {}", educationVO.getEduCode());
        } else {
            LOGGER.info("조회된 데이터: {}", educationDetail.toString());
        }

        // 조회한 데이터를 모델에 추가하여 JSP에서 사용 가능하게 설정
        model.addAttribute("educationDetail", educationDetail);

        // 상세 조회 페이지로 이동
        return "education/educationDetail"; 
    }
    
    
    
    @RequestMapping("/education/educationUpdateView.do")
    public String educationUpdateView(@ModelAttribute("educationVO") EducationVO educationVO, ModelMap model) throws Exception {

        LOGGER.info("수정할 eduCode: {}", educationVO.getEduCode());  // eduCode 로그 출력

        // eduCode를 통해 서비스에서 교육 과정 정보 조회
        EducationVO educationDetail = educationService.selectEducationDetail(educationVO.getEduCode());

        // 분류 및 수료 조건 데이터 조회
        List<EducationVO> categories = educationService.selectAllCategoryList();
        List<EducationMasterVO> completionCriteria = educationService.selectCompletionCriteria();

        // 조회한 데이터를 모델에 추가하여 JSP에서 사용 가능하게 설정
        model.addAttribute("educationDetail", educationDetail);
        model.addAttribute("categories", categories);
        model.addAttribute("completionCriteria", completionCriteria);

        // 수정 페이지로 이동
        return "education/educationUpdate"; // 수정 페이지의 JSP 경로에 맞게 설정
    }
    
    
    
    @RequestMapping("/education/educationUpdate")
    @ResponseBody
    public ResponseEntity<ResponseVO> educationUpdate(@RequestBody EducationVO educationVO) {
        try {
            // 로그 추가
            LOGGER.info("수정할 교육 과정 정보: {}", educationVO.toString());

            // 서비스에서 수정 작업 수행
            educationService.updateEducation(educationVO);

            ResponseVO responseVO = new ResponseVO();
            responseVO.setHttpStatus(HttpStatus.OK);
            responseVO.setMessage("교육 과정이 성공적으로 수정되었습니다.");
            
            return ResponseEntity.status(responseVO.getHttpStatus()).body(responseVO);

        } catch (Exception e) {
            LOGGER.error("교육 과정 수정 중 오류 발생", e);
            ResponseVO responseVO = new ResponseVO();
            responseVO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseVO.setMessage("수정 중 오류가 발생했습니다.");
            
            return ResponseEntity.status(responseVO.getHttpStatus()).body(responseVO);
        }
    }

    
    
    
    // 교육 삭제 요청 처리
    @RequestMapping("/education/deleteEducation")
    @ResponseBody
    public ResponseEntity<ResponseVO> deleteEducation(@RequestBody EducationVO educationVO) {
        int eduCode = educationVO.getEduCode();

        try {
            // 서비스에서 교육 삭제 처리
            educationService.deleteEducation(eduCode);

            ResponseVO responseVO = new ResponseVO();
            responseVO.setHttpStatus(HttpStatus.OK);
            responseVO.setMessage("교육 과정과 관련된 강의가 성공적으로 삭제되었습니다.");

            return ResponseEntity.status(responseVO.getHttpStatus()).body(responseVO);
        } catch (Exception e) {
            LOGGER.error("교육 과정 삭제 중 오류 발생", e);
            ResponseVO responseVO = new ResponseVO();
            responseVO.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseVO.setMessage("교육 과정 삭제 중 오류가 발생했습니다.");
            return ResponseEntity.status(responseVO.getHttpStatus()).body(responseVO);
        }
    }

    
    
    
}
