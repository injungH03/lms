package atos.lms.schedule.web;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import egovframework.com.cmm.LoginVO;

@Controller
@RequestMapping("/education/")
public class InstructorScheduleController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InstructorScheduleController.class);
	
	@Autowired
	InstructorScheduleService instructorScheduleService;
	
	
	 // 스케줄 목록 조회
	@RequestMapping("instructorScheduleList.do")
	public String scheduleList(@ModelAttribute("scheduleSearchVO") InstructorScheduleVO scheduleVO, HttpSession session, ModelMap model) throws Exception {
	    
	    // 세션에서 로그인된 강사 정보 가져오기
	    LoginVO loginUser = (LoginVO) session.getAttribute("loginVO");

	    if (loginUser == null) {
	        // 세션에 로그인 정보가 없으면 로그인 페이지로 리다이렉트
	        return "redirect:/login/LoginUser.do";
	    }

	    String instructorId = loginUser.getId();  // 로그인된 강사 ID 가져오기

	    // 스케줄 목록 조회
	    List<InstructorScheduleVO> scheduleList = instructorScheduleService.getSchedulesByInstructor(instructorId);

	    // 총 레코드 수 설정
	    int totalCount = scheduleList.size();

	    // 모델에 데이터 추가
	    model.addAttribute("totalCount", totalCount);
	    model.addAttribute("scheduleList", scheduleList);

	    return "schedule/instructorSchedule";  // JSP 파일 경로에 맞게 수정
	}
    
	
	
	@RequestMapping("instrScheduleList")
	@ResponseBody
	public List<InstructorScheduleVO> getScheduleList(HttpSession session) throws Exception {
	    // 세션에서 로그인된 강사 정보 가져오기
	    LoginVO loginUser = (LoginVO) session.getAttribute("loginVO");

	    if (loginUser == null) {
	        // 로그인 정보가 없으면 빈 리스트 반환 (혹은 에러 처리)
	        return Collections.emptyList();
	    }

	    String instructorId = loginUser.getId();  // 로그인된 강사 ID 가져오기

	    // 스케줄 목록 조회
	    return instructorScheduleService.getSchedulesByInstructor(instructorId);
	}
	
	
	@RequestMapping("registerSchedule")
	@ResponseBody
	public ResponseEntity<String> registerSchedule(@RequestBody Map<String, Object> requestData, HttpSession session) throws Exception {
	    // 세션에서 로그인된 강사 정보 가져오기
	    LoginVO loginUser = (LoginVO) session.getAttribute("loginVO");

	    if (loginUser == null) {
	        // 로그인 정보가 없으면 에러 반환
	        return ResponseEntity.status(403).body("Unauthorized");
	    }

	    try {
	        InstructorScheduleVO scheduleVO = new InstructorScheduleVO();
	        scheduleVO.setId(loginUser.getId());  // 로그인된 강사의 ID 사용
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
