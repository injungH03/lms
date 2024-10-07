package atos.lms.attendance.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import atos.lms.attendance.service.AllAttendanceService;
import atos.lms.attendance.service.AllAttendanceVO;
import atos.lms.attendance.service.dao.AllAttendanceDAO;

@Service
public class AllAttendanceServiceImpl implements AllAttendanceService {
	
	@Autowired
	AllAttendanceDAO allAttendanceDao;
	
	
	@Override
	public Map<String, Object> selectAttendanceList(AllAttendanceVO attendanceVO) {
	    Map<String, Object> resultMap = new HashMap<>();
	    
	    // 출석 목록 가져오기
	    resultMap.put("resultList", allAttendanceDao.selectAttendanceList(attendanceVO));
	    
	    // 총 레코드 수 (페이징을 위해 필요)
	    resultMap.put("resultCnt", allAttendanceDao.selectAttendanceListCnt(attendanceVO));
	    
	    return resultMap;
	}
	
	
	@Override
	public List<AllAttendanceVO> selectEducationList() {
		return allAttendanceDao.selectEducationList(); 
	}
	
	@Override
	public void updateCheckIn(Map<String, Object> paramMap) {
	    allAttendanceDao.updateCheckIn(paramMap);
	}

	@Override
	public void updateCheckOut(Map<String, Object> paramMap) {
	    allAttendanceDao.updateCheckOut(paramMap);
	}
	
	
	@Override
	public void updateCheckInAll(Map<String, Object> paramMap) {
	    allAttendanceDao.updateCheckInAll(paramMap);
	}

	@Override
	public void updateCheckOutAll(Map<String, Object> paramMap) {
	    allAttendanceDao.updateCheckOutAll(paramMap);
	}

	@Override
	public void updateAllAbsence(List<Integer> attendCodes) {
	    allAttendanceDao.updateAllAbsence(attendCodes);
	}

}
