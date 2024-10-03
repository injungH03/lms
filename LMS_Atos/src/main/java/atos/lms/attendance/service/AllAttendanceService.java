package atos.lms.attendance.service;

import java.util.List;
import java.util.Map;

public interface AllAttendanceService {
	
    // 출석 목록 조회 서비스
    Map<String, Object> selectAttendanceList(AllAttendanceVO attendanceVO);
    
    
    // 교육 목록 조회 서비스
    List<AllAttendanceVO> selectEducationList(); 

}
