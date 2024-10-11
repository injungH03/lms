package atos.lms.schedule.web;

import java.util.Collections;
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
import org.springframework.web.bind.annotation.RequestParam;
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
	    
	    // 강사 선택 여부 확인을 위한 로그 추가
	    if (scheduleVO.getId() != null && !scheduleVO.getId().isEmpty()) {
	        LOGGER.info("Selected instructor ID: {}", scheduleVO.getId());

	        // 스케줄 리스트 조회
	        Map<String, Object> resultMap = instructorScheduleService.getSchedulesByInstructor(scheduleVO);
	        List<InstructorScheduleVO> scheduleList = (List<InstructorScheduleVO>) resultMap.get("scheduleList");

	        // 스케줄 리스트 로그 확인
	        LOGGER.info("Schedule list size: {}", scheduleList.size());
	        for (InstructorScheduleVO schedule : scheduleList) {
	            LOGGER.info("Schedule: {}", schedule);
	        }

	        model.addAttribute("scheduleList", scheduleList);
	        model.addAttribute("totalCount", resultMap.get("totalCount"));
	    } else {
	        LOGGER.info("No instructor selected, displaying empty schedule list.");
	        model.addAttribute("scheduleList", Collections.emptyList());
	        model.addAttribute("totalCount", 0);
	    }

	    return "schedule/instructorSchedule";  // JSP 경로에 맞게 수정 필요
	}

	
	
	
    // MAIN_EVENT 등록
    @RequestMapping("registerMainEvent")
    @ResponseBody
    public ResponseEntity<String> registerMainEvent(@RequestBody InstructorScheduleVO scheduleVO) throws Exception {
        LOGGER.info("registerMainEvent called with requestData: {}", scheduleVO);

        // MAIN_EVENT 등록
        instructorScheduleService.registerMainEvent(scheduleVO);
        return ResponseEntity.ok("success");
    }

    // SUB_EVENT 등록
    @RequestMapping("registerSubEvent")
    @ResponseBody
    public ResponseEntity<String> registerSubEvent(@RequestBody InstructorScheduleVO scheduleVO) throws Exception {
        LOGGER.info("registerSubEvent called with requestData: {}", scheduleVO);

        // SUB_EVENT 등록
        instructorScheduleService.registerSubEvent(scheduleVO);
        return ResponseEntity.ok("success");
    }
    
}
