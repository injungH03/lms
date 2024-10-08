package atos.lms.schedule.web;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;

import atos.lms.schedule.service.InstructorScheduleService;
import atos.lms.schedule.service.InstructorScheduleVO;

@Controller
@RequestMapping("/schedule/")
public class InstructorScheduleController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InstructorScheduleController.class);
	
	@Autowired
	InstructorScheduleService instructorScheduleService;
	
	
	 // 스케줄 목록 조회
    @RequestMapping("instructorScheduleList.do")
    public String scheduleList(@ModelAttribute("scheduleSearchVO") InstructorScheduleVO scheduleVO, ModelMap model) throws Exception {
    	
    	LOGGER.info("scheduleList 접근");
        
        // 페이지 네이션 설정
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(scheduleVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(scheduleVO.getPageUnit());
        paginationInfo.setPageSize(scheduleVO.getPageSize());

        scheduleVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        scheduleVO.setLastIndex(paginationInfo.getLastRecordIndex());
        scheduleVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
       
        // 스케줄 목록 조회
        List<InstructorScheduleVO> scheduleList = instructorScheduleService.getSchedulesByInstructor(scheduleVO.getId());
        LOGGER.info("스케줄 목록 조회 완료");
   
        // 총 레코드 수 설정
        int totalCount = scheduleList.size();
        paginationInfo.setTotalRecordCount(totalCount);
        
        LOGGER.info("선택된 강사 ID: " + scheduleVO.getId());
        LOGGER.info("Start Date: " + scheduleVO.getScheduleDate());

        // 로그: PaginationInfo 객체의 설정 값 출력
        LOGGER.info("첫 레코드 인덱스: " + paginationInfo.getFirstRecordIndex());
        LOGGER.info("마지막 레코드 인덱스: " + paginationInfo.getLastRecordIndex());
        LOGGER.info("총 레코드 수: " + paginationInfo.getTotalRecordCount());
        
        // 모델에 데이터 추가
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("scheduleList", scheduleList);
        
        return "schedule/instructorSchedule";  // JSP 파일 경로에 맞게 수정
    }
    
    
    @RequestMapping("instrScheduleList")
    @ResponseBody
    public List<InstructorScheduleVO> getScheduleList() throws Exception {
        // 예시로 강사 ID를 고정했지만, 실제 로그인된 강사 ID로 대체 필요
        List<InstructorScheduleVO> scheduleList = instructorScheduleService.getSchedulesByInstructor("instructorId");
        return scheduleList;
    }
    
    
    // 스케줄 등록 처리
    @RequestMapping("registerSchedule")
    @ResponseBody
    public ResponseEntity<String> registerSchedule(@RequestBody Map<String, Object> requestData) throws Exception {
        System.out.println("registerSchedule");

        try {
            InstructorScheduleVO scheduleVO = new InstructorScheduleVO();
            scheduleVO.setId((String) requestData.get("id"));
            scheduleVO.setScheduleDate(LocalDate.parse((String) requestData.get("scheduleDate")));
            
            // 스케줄 등록 서비스 호출
            instructorScheduleService.registerSchedule(scheduleVO);
            
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("fail");
        }
    }

}
