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
	
	
	@RequestMapping("educationPopup.do")
	public String educationPopup(ModelMap model) throws Exception {
		
		
		return "atos/lecture/educationPopup";
	}
	
	@RequestMapping("instructorPopup.do")
	public String instructorPopup(ModelMap model) throws Exception {
		
		
		return "atos/lecture/instructorPopup";
	}
	
}



