package atos.lms.attendance.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public interface AllAttendanceService {

	// 출석 목록 조회 서비스
	Map<String, Object> selectAttendanceList(AllAttendanceVO attendanceVO);

	// 교육 목록 조회 서비스
	List<AllAttendanceVO> selectEducationList();

	void updateCheckIn(Map<String, Object> paramMap);

	void updateCheckOut(Map<String, Object> paramMap);

	// 특정 출석 코드로 출석 정보 조회
	AllAttendanceVO selectAttendanceByCode(int attendCode);

	void updateCheckInAll(Map<String, Object> paramMap);

	void updateCheckOutAll(Map<String, Object> paramMap);

	void updateAllAbsence(List<Integer> attendCodes);
	
	 // 엑셀 다운로드용 출석 목록 조회 (검색 조건 포함)
    void attendanceListExcelDown(HttpServletResponse response, AllAttendanceVO attendanceVO) throws Exception;

}
