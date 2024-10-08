package atos.lms.schedule.service.impl;

import java.util.List;

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
    public List<InstructorScheduleVO> getSchedulesByInstructor(String instructorId) {
        return instructorScheduleDao.selectScheduleByInstructor(instructorId);
    }

    @Override
    public void registerSchedule(InstructorScheduleVO scheduleVO) {
        instructorScheduleDao.insertSchedule(scheduleVO);
    }

}
