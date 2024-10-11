package atos.lms.schedule.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import atos.lms.schedule.service.InstructorScheduleService;
import atos.lms.schedule.service.InstructorScheduleVO;
import atos.lms.schedule.service.dao.InstructorScheduleDAO;

@Service
public class InstructorScheduleImpl implements InstructorScheduleService {
	
	@Autowired
	InstructorScheduleDAO instructorScheduleDao;
	
	
    @Override
    public Map<String, Object> getSchedulesByInstructor(InstructorScheduleVO scheduleVO) {
        Map<String, Object> resultMap = new HashMap<>();

        // 스케줄 리스트 조회
        List<InstructorScheduleVO> scheduleList = instructorScheduleDao.selectScheduleByInstructor(scheduleVO);
        // 스케줄 총 개수 조회
        int totalCount = instructorScheduleDao.selectScheduleCountByInstructor(scheduleVO);

        // 리스트와 개수를 맵에 담음
        resultMap.put("scheduleList", scheduleList);
        resultMap.put("totalCount", totalCount);

        return resultMap;
    }
    
    @Override
    public Map<String, InstructorScheduleVO> selectAllInstructors(InstructorScheduleVO scheduleVO) {
        // 강사 목록을 리스트로 받아옴 (DAO는 리스트로 반환)
        List<InstructorScheduleVO> instructorList = instructorScheduleDao.selectAllInstructors(scheduleVO);

        // 리스트를 맵으로 변환
        Map<String, InstructorScheduleVO> instructorMap = new HashMap<>();
        for (InstructorScheduleVO instructor : instructorList) {
            instructorMap.put(instructor.getId(), instructor);  // 강사 ID를 키로 사용
        }

        return instructorMap;
    }

    
    @Override
    public void registerMainEvent(InstructorScheduleVO scheduleVO) {
        instructorScheduleDao.insertMainEvent(scheduleVO);
    }

    @Override
    public void registerSubEvent(InstructorScheduleVO scheduleVO) {
        instructorScheduleDao.insertSubEvent(scheduleVO);
    }
    
    
    
}


