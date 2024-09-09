package atos.lms.education.web;

import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        // 교육 목록 조회
        Map<String, Object> map = educationService.selectEducationList(educationVO);
        
		// 데이터 출력 부분
		System.out.println("educationList resultList: " + map.get("resultList"));
		System.out.println("educationList resultCnt: " + map.get("resultCnt"));
		
        int totalCount = Integer.parseInt(String.valueOf(map.get("resultCnt")));
        
        paginationInfo.setTotalRecordCount(totalCount);
        

        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("paginationInfo", paginationInfo);

        return "education/educationList";
    }

}
