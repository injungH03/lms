package atos.lms.schedule.service.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import atos.lms.schedule.service.InstructorScheduleVO;

@Repository
public interface InstructorScheduleDAO {
	
    // 강사의 스케줄 조회 (강사 ID를 통해 조회)
    List<InstructorScheduleVO> selectScheduleByInstructor(String instructorId);

    // 스케줄 등록
    void insertSchedule(InstructorScheduleVO scheduleVO);

}
