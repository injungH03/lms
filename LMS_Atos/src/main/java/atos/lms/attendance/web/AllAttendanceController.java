package atos.lms.attendance.web;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<String> checkIn(@RequestParam("attendCode") int attendCode) throws Exception {
        System.out.println("checkIn");
        
        try {
            LocalTime inTime = LocalTime.now();
            LocalDate attendDate = LocalDate.now();

            // 시간 형식을 HH:mm으로 변환
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String formattedInTime = inTime.format(formatter); // 시간을 HH:mm 형식으로 변환
            
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("attendCode", attendCode);
            paramMap.put("inTime", formattedInTime); // 변환된 시간으로 저장
            paramMap.put("attendDate", attendDate);
            
            // 입실 처리 서비스 호출
            allAttendanceService.updateCheckIn(paramMap);
            
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("fail");
        }
    }

    // 퇴실 처리
    @RequestMapping("checkOut")
    @ResponseBody
    public ResponseEntity<String> checkOut(@RequestBody Map<String, Object> requestData) throws Exception {
        try {
            List<Integer> attendCodes = (List<Integer>) requestData.get("attendCodes");
            LocalTime outTime = LocalTime.now();

            // 시간 형식을 HH:mm으로 변환
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String formattedOutTime = outTime.format(formatter); // 시간을 HH:mm 형식으로 변환

            // 입실 여부 확인
            for (Integer attendCode : attendCodes) {
                AllAttendanceVO attendanceVO = allAttendanceService.selectAttendanceByCode(attendCode);
                if (attendanceVO.getInTime() == null) {
                    // 입실 처리가 안 되었을 경우 400 에러 반환
                    return ResponseEntity.status(400).body("입실 처리가 완료되지 않았습니다.");
                }
            }

            // 입실 처리가 완료된 경우에만 퇴실 처리
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("attendCodes", attendCodes);
            paramMap.put("outTime", formattedOutTime); // 변환된 시간으로 저장
            allAttendanceService.updateCheckOutAll(paramMap);

            return ResponseEntity.ok("success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("fail");
        }
    }
    
    
    // 전체 입실 처리
    @RequestMapping("checkInAll")
    @ResponseBody
    public ResponseEntity<String> checkInAll(@RequestBody Map<String, Object> requestData) throws Exception {
    	System.out.println("checkInAll");
        try {
            allAttendanceService.updateCheckInAll(requestData);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("fail");
        }
    }

    // 전체 퇴실 처리
    @RequestMapping("checkOutAll")
    @ResponseBody
    public ResponseEntity<String> checkOutAll(@RequestBody Map<String, Object> requestData) throws Exception {
    	System.out.println("checkOutAll");
        try {
            allAttendanceService.updateCheckOutAll(requestData);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("fail");
        }
    }

 // 결석 처리
    @RequestMapping("allAbsence")
    @ResponseBody
    public ResponseEntity<String> markAbsent(@RequestBody Map<String, Object> requestData) throws Exception {
        System.out.println("allAbsence");
        try {
            List<Integer> attendCodes = (List<Integer>) requestData.get("attendCodes");
            
            // 결석 처리 호출 (상태 업데이트 및 입퇴실 시간 초기화)
            allAttendanceService.updateAllAbsence(attendCodes);

            return ResponseEntity.ok("success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("fail");
        }
    }
    
    
    @RequestMapping("attendanceExcelDownload")
    public void attendanceExcelDownload(HttpServletResponse response, AllAttendanceVO attendanceVO) throws Exception {
        allAttendanceService.attendanceListExcelDown(response, attendanceVO);  
    }
    
    
    
    
    
    
    

}
