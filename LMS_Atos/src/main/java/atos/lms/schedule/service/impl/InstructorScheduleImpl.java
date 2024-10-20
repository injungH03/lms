package atos.lms.schedule.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import atos.lms.schedule.service.InstructorScheduleService;
import atos.lms.schedule.service.InstructorScheduleVO;
import atos.lms.schedule.service.dao.InstructorScheduleDAO;

@Service
public class InstructorScheduleImpl implements InstructorScheduleService {
	
	@Autowired
	InstructorScheduleDAO instructorScheduleDao;
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InstructorScheduleImpl.class);
	
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
    public Map<String, Object> getAllSchedules(InstructorScheduleVO scheduleVO) {
        Map<String, Object> resultMap = new HashMap<>();

        // 모든 강사의 스케줄 리스트 조회
        List<InstructorScheduleVO> scheduleList = instructorScheduleDao.selectAllSchedules(scheduleVO);
        // 스케줄 총 개수 조회
        int totalCount = scheduleList.size();  // 전체 스케줄이므로 직접 개수를 구함

        // 리스트와 개수를 맵에 담음
        resultMap.put("scheduleList", scheduleList);
        resultMap.put("totalCount", totalCount);

        return resultMap;
    }
    
    
    @Override
    public Map<String, Object> registerSchedule(InstructorScheduleVO scheduleVO) {
        Map<String, Object> map = new HashMap<>();
        
        // 스케줄 등록
        instructorScheduleDao.insertSchedule(scheduleVO);
        
        map.put("message", "스케줄 등록 완료");
        
        return map;
    }
    
    
    @Override
    public Map<String, Object> updateSchedule(InstructorScheduleVO scheduleVO) {
        Map<String, Object> map = new HashMap<>();
        instructorScheduleDao.updateSchedule(scheduleVO);
        map.put("message", "스케줄 수정 완료");
        return map;
    }
    
    
    @Override
    public Map<String, Object> deleteSchedule(InstructorScheduleVO scheduleVO) {
        Map<String, Object> map = new HashMap<>();
        instructorScheduleDao.deleteSchedule(scheduleVO);
        map.put("message", "스케줄 삭제 완료");
        return map;
    }

}


