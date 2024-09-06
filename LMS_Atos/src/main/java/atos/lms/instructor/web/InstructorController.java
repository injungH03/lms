package atos.lms.instructor.web;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import atos.lms.common.utl.ResponseVO;
import atos.lms.instructor.service.InstructorMasterVO;
import atos.lms.instructor.service.InstructorService;
import atos.lms.instructor.service.InstructorVO;
import egovframework.com.utl.sim.service.EgovFileScrty;



@Controller
@RequestMapping("/member/")
public class InstructorController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InstructorController.class);
	
	
	@Resource(name = "InstructorService")
	private InstructorService instructorService;
    
    @RequestMapping("instructorList.do")
    public String instructorList(@ModelAttribute("searchVO") InstructorVO instructorVO,  ModelMap model) throws Exception {
    	
    	PaginationInfo paginationInfo = new PaginationInfo();
		
    	paginationInfo.setCurrentPageNo(instructorVO.getPageIndex());
    	paginationInfo.setRecordCountPerPage(instructorVO.getPageUnit());
    	paginationInfo.setPageSize(instructorVO.getPageSize());
    	
    	instructorVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
    	instructorVO.setLastIndex(paginationInfo.getLastRecordIndex());
    	instructorVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
    	
    	Map<String, Object> map = instructorService.selectInstructorList(instructorVO);

    	int totalcount = Integer.parseInt(String.valueOf(map.get("resultCnt")));
    	
    	paginationInfo.setTotalRecordCount(totalcount);

    	List<InstructorMasterVO> status = instructorService.selectStatusCode();
    	
    	model.addAttribute("resultList", map.get("resultList"));
    	model.addAttribute("paginationInfo", paginationInfo);
    	model.addAttribute("status", status);
    	
    	return "instructor/instructorList";
    }
    
	@RequestMapping("instructorListExcelDown")
	public void instructorListExcelDown(HttpServletResponse response, InstructorVO instructorVO) throws Exception {
		
		instructorService.instructorListExcelDown(response, instructorVO);
		
	}
	
	@RequestMapping("instructorRegistView.do")
	public String instructorRegistView(@ModelAttribute("searchVO") InstructorVO instructorVO, ModelMap model) throws Exception {


		return "instructor/instructorRegist";
	}
	
	@RequestMapping("updateInstructorStatus")
	@ResponseBody
	public ResponseEntity<String> updateInstructorStatus(@RequestParam("ids") String ids, @RequestParam("status") String status)
			throws Exception {
		
		System.out.println("ids = " + ids);
		System.out.println("status = " + status);
		
		instructorService.updateInstructorStatus(ids, status);
		
		return ResponseEntity.ok("상태 변경 완료");
	}

	@RequestMapping("instructorInsert")
	@ResponseBody
	public ResponseEntity<ResponseVO> instructorInsert(@RequestBody InstructorVO instructorVO) throws Exception {

//		System.out.println(">>>>입력값  =  " + instructorVO.toString());

		// 1. 입력한 비밀번호 암호화
		String enpassword = EgovFileScrty.encryptPassword(instructorVO.getPassword(), instructorVO.getId());

		// 2. 암호화한 비밀번호 재설정
		instructorVO.setPassword(enpassword);
		
//		System.out.println(">>>>넘어가는값  =  " + instructorVO.toString());
		instructorService.instructorInsert(instructorVO);

		ResponseVO responseVO = new ResponseVO();
		responseVO.setHttpStatus(HttpStatus.OK);
		responseVO.setMessage("등록 완료");

		return ResponseEntity.status(responseVO.getHttpStatus()).body(responseVO);
	}
    

	@RequestMapping("instructorDetail.do")
	public String instructorDetail(@ModelAttribute("searchVO") InstructorVO instructorVO, ModelMap model) throws Exception {

//    	System.out.println("넘어온 데이터  = " + memberVO);

		model.addAttribute("instructor", instructorService.selectInstructorKey(instructorVO));

		return "instructor/instructorDetail";
	}

	@RequestMapping("instructorUpdateView.do")
	public String memberUpdateView(@ModelAttribute("searchVO") InstructorVO instructorVO, ModelMap model) throws Exception {

//    	System.out.println("넘어온 데이터  = " + memberVO);

		model.addAttribute("instructor", instructorService.selectInstructorKey(instructorVO));
		
		return "instructor/instructorUpdt";
	}
	
	
	@RequestMapping("instructorDelete")
	@ResponseBody
	public ResponseEntity<ResponseVO> instructorDelete(@RequestBody InstructorVO instructorVO) throws Exception {

		instructorService.instructorDelete(instructorVO);

		ResponseVO responseVO = new ResponseVO();
		responseVO.setHttpStatus(HttpStatus.OK);
		responseVO.setMessage("삭제 완료");

		return ResponseEntity.status(responseVO.getHttpStatus()).body(responseVO);
	}

	@RequestMapping("instructorUpdate")
	@ResponseBody
	public ResponseEntity<ResponseVO> instructorUpdate(@RequestBody InstructorVO instructorVO) throws Exception {

//    	System.out.println(">>>>입력값  =  " + memberVO.toString());
		
		instructorService.updateInstructor(instructorVO);

		ResponseVO responseVO = new ResponseVO();
		responseVO.setHttpStatus(HttpStatus.OK);
		responseVO.setMessage("수정 완료");

		return ResponseEntity.status(responseVO.getHttpStatus()).body(responseVO);
	}




}
