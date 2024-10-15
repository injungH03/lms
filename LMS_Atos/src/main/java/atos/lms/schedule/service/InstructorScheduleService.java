package atos.lms.schedule.service;

import java.util.Map;

public interface InstructorScheduleService {

	// 강사의 스케줄 조회
	Map<String, Object> getSchedulesByInstructor(InstructorScheduleVO scheduleVO);

	// 모든 강사 목록 조회
    Map<String, InstructorScheduleVO> selectAllInstructors(InstructorScheduleVO scheduleVO);
    
    // 모든 강사의 스케줄 조회
    Map<String, Object> getAllSchedules(InstructorScheduleVO scheduleVO);

    // 등록
    Map<String, Object> registerSchedule(InstructorScheduleVO scheduleVO);
    
    // 수정
    Map<String, Object> updateSchedule(InstructorScheduleVO scheduleVO); 
    
    // 삭제
    Map<String, Object> deleteSchedule(InstructorScheduleVO scheduleVO);

}