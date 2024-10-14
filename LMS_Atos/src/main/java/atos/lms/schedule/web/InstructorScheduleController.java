package atos.lms.schedule.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@RequestMapping("/education/")
public class InstructorScheduleController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InstructorScheduleController.class);
	
	@Autowired
	InstructorScheduleService instructorScheduleService;
	
	@RequestMapping("instructorScheduleList.do")
	public String scheduleList(@ModelAttribute("scheduleSearchVO") InstructorScheduleVO scheduleVO, ModelMap model) throws Exception {
	    // 강사 목록 조회
	    Map<String, InstructorScheduleVO> instructorMap = instructorScheduleService.selectAllInstructors(scheduleVO);
	    model.addAttribute("instructorList", instructorMap);

	    LOGGER.info("Instructor map size: {}", instructorMap.size());
	    
	    // 강사 선택 여부 확인
	    if (scheduleVO.getId() != null && !scheduleVO.getId().isEmpty()) {
	        LOGGER.info("Selected instructor ID: {}", scheduleVO.getId());

	        // 선택된 강사의 스케줄 리스트 조회
	        Map<String, Object> resultMap = instructorScheduleService.getSchedulesByInstructor(scheduleVO);
	        List<InstructorScheduleVO> scheduleList = (List<InstructorScheduleVO>) resultMap.get("scheduleList");

	        LOGGER.info("Schedule list size: {}", scheduleList.size());
	        for (InstructorScheduleVO schedule : scheduleList) {
	            LOGGER.info("Schedule: {}", schedule);
	        }

	        model.addAttribute("scheduleList", scheduleList);
	        model.addAttribute("totalCount", resultMap.get("totalCount"));
	    } else {
	        LOGGER.info("No instructor selected, displaying all schedules.");

	        // 전체 강사의 스케줄 리스트 조회
	        Map<String, Object> resultMap = instructorScheduleService.getAllSchedules(scheduleVO);
	        List<InstructorScheduleVO> scheduleList = (List<InstructorScheduleVO>) resultMap.get("scheduleList");

	        LOGGER.info("All schedules list size: {}", scheduleList.size());
	        for (InstructorScheduleVO schedule : scheduleList) {
	            LOGGER.info("Schedule: {}", schedule);
	        }

	        model.addAttribute("scheduleList", scheduleList);
	        model.addAttribute("totalCount", resultMap.get("totalCount"));
	    }

	    return "schedule/instructorSchedule";  // JSP 경로에 맞게 수정 필요
	}
	

	@RequestMapping("loadInstructorModal.do")
	public String loadInstructorModal(@ModelAttribute("scheduleSearchVO") InstructorScheduleVO scheduleVO, ModelMap model) throws Exception {
	    // 강사 목록을 모달에 전달
	    Map<String, InstructorScheduleVO> instructorMap = instructorScheduleService.selectAllInstructors(scheduleVO);
	    model.addAttribute("instructorList", instructorMap);

	    // 모달 JSP 파일 반환
	    return "schedule/instructorModal"; // JSP 파일 경로에 맞게 수정 필요
	}
	
	
	
	 // 새로운 스케줄 저장 요청을 처리
	@RequestMapping("registerSchedule")
	@ResponseBody
	public ResponseEntity<Map<String, String>> registerSchedule(@RequestBody InstructorScheduleVO scheduleVO) throws Exception {
	    Map<String, Object> map = instructorScheduleService.registerSchedule(scheduleVO);
	    
	    Map<String, String> response = new HashMap<>();
	    response.put("message", (String) map.get("message"));
	    
	    return ResponseEntity.ok(response);
	}
	
	
}
