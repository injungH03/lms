package atos.lms.lecture.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import atos.lms.lecture.service.LectureService;

@Controller
@RequestMapping("/education/")
public class LectureController {
	
	@Resource(name = "LectureService")
	LectureService lectureService;
	
	@RequestMapping("lectureList.do")
	public String lectureList(ModelMap model) throws Exception {
		
		
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



