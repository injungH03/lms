package atos.lms.attendance.service.impl;

import java.util.HashMap;
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

}
