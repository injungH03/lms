package atos.lms.attendance.service;

import java.util.Map;

public interface AllAttendanceService {
	
    // 출석 목록 조회 서비스
    Map<String, Object> selectAttendanceList(AllAttendanceVO attendanceVO);

}
