package atos.lms.schedule.service;

import java.util.List;

public interface InstructorScheduleService {
	
    // 강사의 스케줄 조회
    List<InstructorScheduleVO> getSchedulesByInstructor(String instructorId);

    // 스케줄 등록
    void registerSchedule(InstructorScheduleVO scheduleVO);

}
