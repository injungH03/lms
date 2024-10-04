package atos.lms.attendance.web;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String allAttendanceList(@ModelAttribute("attendanceSearchVO") AllAttendanceVO attendanceVO, ModelMap model) throws Exception {
    	
    	LOGGER.info("allAttendanceList 접근");
        
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
        // 교육 목록 조회 
        List<AllAttendanceVO> educationList = allAttendanceService.selectEducationList();
       
        
        System.out.println("allAttendanceList resultList: " + map.get("resultList"));
        System.out.println("allAttendanceList resultCnt: " + map.get("resultCnt"));
   
        
        // 총 레코드 수 설정
        int totalCount = Integer.parseInt(String.valueOf(map.get("resultCnt")));
        paginationInfo.setTotalRecordCount(totalCount);
        
        LOGGER.info("선택된 강의 코드: " + attendanceVO.getLectureCode());
        LOGGER.info("Start Date: " + attendanceVO.getSrcStartDate());
        LOGGER.info("End Date: " + attendanceVO.getSrcEndDate());
        LOGGER.info("검색 조건: " + attendanceVO.getSearchCnd());
        LOGGER.info("검색어: " + attendanceVO.getSearchWrd());

        // 로그: PaginationInfo 객체의 설정 값 출력
        LOGGER.info("첫 레코드 인덱스: " + paginationInfo.getFirstRecordIndex());
        LOGGER.info("마지막 레코드 인덱스: " + paginationInfo.getLastRecordIndex());
        LOGGER.info("총 레코드 수: " + paginationInfo.getTotalRecordCount());
        
        // 모델에 데이터 추가
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("resultList", map.get("resultList"));
        model.addAttribute("educationList", educationList);
        
        return "attendance/allAttendanceList";  // JSP 파일 경로에 맞게 수정
    }
    
    
 // 입실 처리
    @RequestMapping("checkIn")
    @ResponseBody
    public String checkIn(@RequestParam("attendCode") int attendCode) {
    	System.out.println("checkIn");
        LocalTime inTime = LocalTime.now();
        LocalDate attendDate = LocalDate.now();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("attendCode", attendCode);
        paramMap.put("inTime", inTime);
        paramMap.put("attendDate", attendDate);
        allAttendanceService.updateCheckIn(paramMap);
        return "success";
    }

    // 퇴실 처리
    @RequestMapping("checkOut")
    @ResponseBody
    public String checkOut(@RequestParam("attendCode") int attendCode) {
    	System.out.println("checkOut");
        LocalTime outTime = LocalTime.now();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("attendCode", attendCode);
        paramMap.put("outTime", outTime);
        allAttendanceService.updateCheckOut(paramMap);
        return "success";
    }
    
    
    @RequestMapping("checkInAll")
    @ResponseBody
    public String checkInAll(@RequestParam List<Integer> attendCodes, @RequestParam String inTime, @RequestParam String attendDate) {
    	System.out.println("checkInAll");
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("attendCodes", attendCodes);
            paramMap.put("inTime", inTime);
            paramMap.put("attendDate", attendDate);
            allAttendanceService.updateCheckInAll(paramMap);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping("checkOutAll")
    @ResponseBody
    public String checkOutAll(@RequestParam List<Integer> attendCodes, @RequestParam String outTime) {
    	System.out.println("checkOutAll");
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("attendCodes", attendCodes);
            paramMap.put("outTime", outTime);
            allAttendanceService.updateCheckOutAll(paramMap);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping("allAbsence")
    @ResponseBody
    public String markAbsent(@RequestParam List<Integer> attendCodes) {
    	System.out.println("allAbsence");
        try {
            allAttendanceService.updateAllAbsence(attendCodes);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

}
