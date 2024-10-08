package atos.lms.lecture.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import atos.lms.common.service.CategoryService;
import atos.lms.common.service.CategoryVO;
import atos.lms.common.sort.SortFieldValidator;
import atos.lms.common.utl.ResponseVO;
import atos.lms.instructor.service.InstructorVO;
import atos.lms.lecture.service.LectureInsDTO;
import atos.lms.lecture.service.LectureService;
import atos.lms.lecture.service.LectureVO;

@Controller
@RequestMapping("/education/")
public class LectureController {
	
	@Autowired
	LectureService lectureService;
	
	@Autowired
	CategoryService categoryservice;
	
	
	@RequestMapping("lectureList.do")
	public String lectureList(@ModelAttribute("searchVO") LectureVO lectureVO, ModelMap model) throws Exception {
		
		List<CategoryVO> categoryList = categoryservice.selectCodeMain(null);
		
		
		PaginationInfo paginationInfo = new PaginationInfo();
		
    	paginationInfo.setCurrentPageNo(lectureVO.getPageIndex());
    	paginationInfo.setRecordCountPerPage(lectureVO.getPageUnit());
    	paginationInfo.setPageSize(lectureVO.getPageSize());
    	
    	lectureVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
    	lectureVO.setLastIndex(paginationInfo.getLastRecordIndex());
    	lectureVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
    	
    	Map<String, Object> map = lectureService.selectLecture(lectureVO);

    	int totalcount = Integer.parseInt(String.valueOf(map.get("resultCnt")));
    	
    	paginationInfo.setTotalRecordCount(totalcount);
		
    	
    	model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("totalcount", totalcount);
		model.addAttribute("resultList", map.get("resultList"));
		
		return "lecture/lectureList";
	}
	
	@RequestMapping("lectureRegist.do")
	public String lectureRegist(ModelMap model) throws Exception {
		
		
		return "lecture/lectureRegist";
	}
	
	@RequestMapping("lectureDetail.do")
	public String lectureDetail(ModelMap model) throws Exception {
		
		
		model.addAttribute("page", "lectureDetail");
		
		return "lecture/lectureDetail";
	}

	@RequestMapping("lectureStudentList.do")
	public String lectureStudentList(ModelMap model) throws Exception {
		
		
		model.addAttribute("page", "lectureStudentList");
		
		return "lecture/lectureStudentList";
	}
	
	@RequestMapping("attendanceInfo.do")
	public String attendanceInfo(ModelMap model) throws Exception {
		
		
		model.addAttribute("page", "attendanceInfo");
		
		return "lecture/lectureAttendance";
	}
	
	
	
	
	@RequestMapping("educationPopup.do")
	public String educationPopup(@ModelAttribute("searchVO") LectureVO lectureVO, ModelMap model) throws Exception {
		
		List<CategoryVO> categoryList = categoryservice.selectCodeMain(null);
		
		
		PaginationInfo paginationInfo = new PaginationInfo();
		
    	paginationInfo.setCurrentPageNo(lectureVO.getPageIndex());
    	paginationInfo.setRecordCountPerPage(lectureVO.getPageUnit());
    	paginationInfo.setPageSize(lectureVO.getPageSize());
    	
    	lectureVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
    	lectureVO.setLastIndex(paginationInfo.getLastRecordIndex());
    	lectureVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
    	
    	Map<String, Object> map = lectureService.selectEducation(lectureVO);

    	int totalcount = Integer.parseInt(String.valueOf(map.get("resultCnt")));
    	
    	paginationInfo.setTotalRecordCount(totalcount);
    	
    	model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("resultList", map.get("resultList"));
		
		return "atos/lecture/popupEducation";
	}
	
	@RequestMapping("instructorPopup.do")
	public String instructorPopup(@ModelAttribute("searchVO") LectureInsDTO lectureInsDTO, ModelMap model) throws Exception {
		
    	Map<String, Object> map = lectureService.selectInstructor(lectureInsDTO);
    	
    	
		model.addAttribute("resultList", map.get("resultList"));
    	
		return "atos/lecture/popupInstructor";
	}
	
	@RequestMapping("studentListPopup.do")
	public String studentListPopup(ModelMap model) throws Exception {
		
		
		return "atos/lecture/popupStudentList";
	}
	
	@RequestMapping("studentDetailPopup.do")
	public String studentDetailPopup(ModelMap model) throws Exception {
		
		
		return "atos/lecture/popupStudentDetail";
	}
	
	@RequestMapping("studentLecturePopup.do")
	public String studentLecturePopup(ModelMap model) throws Exception {
		
		
		return "atos/lecture/popupStudentLecture";
	}
	
	
	@RequestMapping("instructorPopupSave")
	@ResponseBody
	public ResponseEntity<ResponseVO> instructorPopupSave(@RequestBody LectureInsDTO lectureInsDTO) throws Exception {
		
		System.out.println(">>>>>데이터 = " + lectureInsDTO);
		
		Map<String, Object> map = lectureService.saveInstructor(lectureInsDTO);
		
		ResponseVO responseVO = new ResponseVO();
		responseVO.setHttpStatus(HttpStatus.OK);
		responseVO.setMessage((String) map.get("message"));

		return ResponseEntity.status(responseVO.getHttpStatus()).body(responseVO);
	}
	
}



