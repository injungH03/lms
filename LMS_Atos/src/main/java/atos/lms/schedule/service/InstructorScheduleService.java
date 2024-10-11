package atos.lms.schedule.service;

import java.util.Map;

public interface InstructorScheduleService {

	// 강사의 스케줄 조회
	Map<String, Object> getSchedulesByInstructor(InstructorScheduleVO scheduleVO);

	 // 모든 강사 목록 조회
    Map<String, InstructorScheduleVO> selectAllInstructors(InstructorScheduleVO scheduleVO);

	// MAIN_EVENT 등록
	void registerMainEvent(InstructorScheduleVO scheduleVO);

	// SUB_EVENT 등록
	void registerSubEvent(InstructorScheduleVO scheduleVO);

}