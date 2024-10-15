package atos.lms.schedule.service.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import atos.lms.schedule.service.InstructorScheduleVO;

@Repository
public interface InstructorScheduleDAO {
	
    // 강사의 스케줄 리스트 조회
    List<InstructorScheduleVO> selectScheduleByInstructor(InstructorScheduleVO scheduleVO);
    
    // 모든 강사 목록 조회 (InstructorScheduleVO 사용)
    List<InstructorScheduleVO> selectAllInstructors(InstructorScheduleVO scheduleVO);
    
    // 모든 강사의 스케줄 조회
    List<InstructorScheduleVO> selectAllSchedules(InstructorScheduleVO scheduleVO);

    // 스케줄 총 개수 조회
    int selectScheduleCountByInstructor(InstructorScheduleVO scheduleVO);

    void insertSchedule(InstructorScheduleVO scheduleVO);
    
    void updateSchedule(InstructorScheduleVO scheduleVO);
    
    void deleteSchedule(InstructorScheduleVO scheduleVO);

}
