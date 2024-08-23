package atos.lms.exam.web;

import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import atos.lms.exam.service.TestBoardService;
import atos.lms.exam.service.TestBoardVO;


@Controller
public class TestMainController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestMainController.class);
	
	
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;
    
    
    @RequestMapping("/test/main.do")
    public String main(ModelMap model) throws Exception {
    	
    	model.addAttribute("title", "Custom Main Page Title");
    	
    	return "test/maincontent";
    }
    

}
