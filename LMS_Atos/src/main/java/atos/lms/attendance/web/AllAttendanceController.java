package atos.lms.attendance.web;

import java.util.Map;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import atos.lms.attendance.service.AllAttendanceService;
import atos.lms.attendance.service.AllAttendanceVO;

@Controller
@RequestMapping("/attendance/")
public class AllAttendanceController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AllAttendanceController.class);
	
    @Autowired
    AllAttendanceService allAttendanceService;

    // 출석 목록 조회
    @RequestMapping("allAttendanceList.do")
    public String allAttendanceList(@ModelAttribute("searchVO") AllAttendanceVO attendanceVO, ModelMap model) throws Exception {
    	
    	System.out.println("allAttendanceList 접근");
        
        // 페이지 네이션 설정
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(attendanceVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(attendanceVO.getPageUnit());
        paginationInfo.setPageSize(attendanceVO.getPageSize());

        attendanceVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        attendanceVO.setLastIndex(paginationInfo.getLastRecordIndex());
        attendanceVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
        
        // 출석 목록 조회
        Map<String, Object> map = allAttendanceService.selectAttendanceList(attendanceVO);
        
        System.out.println("allAttendanceList resultList: " + map.get("resultList"));
        System.out.println("allAttendanceList resultCnt: " + map.get("resultCnt"));
		      
        
        // 총 레코드 수 설정
        int totalCount = Integer.parseInt(String.valueOf(map.get("resultCnt")));
        paginationInfo.setTotalRecordCount(totalCount);
        
        // 모델에 데이터 추가
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("resultList", map.get("resultList"));
        
        return "attendance/allAttendanceList";  // JSP 파일 경로에 맞게 수정
    }

}
