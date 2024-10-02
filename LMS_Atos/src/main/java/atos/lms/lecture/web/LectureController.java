package atos.lms.lecture.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import atos.lms.common.service.CategoryService;
import atos.lms.common.service.CategoryVO;
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
		
		List<String> typelist = new ArrayList<String>();
		typelist.add("A001");
		typelist.add("A002");
		typelist.add("A003");
		
		CategoryVO categoryVO = CategoryVO.builder().mtype(typelist).build();
		
		List<CategoryVO> categoryList = categoryservice.selectCodeMain(categoryVO);
		
		
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
		
    	
    	List<LectureVO> list = (List<LectureVO>) map.get("resultList");
    	
    	list.forEach(lecture -> System.out.println("데이터 = " + lecture));
    	
    	
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
	public String educationPopup(ModelMap model) throws Exception {
		
		
		return "atos/lecture/popupEducation";
	}
	
	@RequestMapping("instructorPopup.do")
	public String instructorPopup(ModelMap model) throws Exception {
		
		
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
	
}



