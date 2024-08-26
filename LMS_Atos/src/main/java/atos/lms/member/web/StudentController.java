package atos.lms.member.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class StudentController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);
	
    
    @RequestMapping("/test/studentList.do")
    public String studentList(ModelMap model) throws Exception {
    	
    	
    	return "test/maincontent";
    }
    

}
