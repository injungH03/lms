package atos.lms.attendance.service.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import atos.lms.attendance.service.AllAttendanceExcelVO;
import atos.lms.attendance.service.AllAttendanceVO;

@Repository
public interface AllAttendanceDAO {

	// 출석 목록 조회
	List<AllAttendanceVO> selectAttendanceList(AllAttendanceVO attendanceVO);

	// 출석 목록 총 개수 조회 (페이징을 위해)
	int selectAttendanceListCnt(AllAttendanceVO attendanceVO);

	// 교육 목록 조회
	List<AllAttendanceVO> selectEducationList();

	void updateCheckIn(Map<String, Object> paramMap);

	void updateCheckOut(Map<String, Object> paramMap);

	// 특정 출석 코드로 출석 정보 조회
	AllAttendanceVO selectAttendanceByCode(int attendCode);

	void updateCheckInAll(Map<String, Object> paramMap);

	void updateCheckOutAll(Map<String, Object> paramMap);

	void updateAllAbsence(List<Integer> attendCodes);
	
    // 엑셀 다운로드용 출석 목록 조회 (검색 조건 포함)
    List<AllAttendanceExcelVO> selectAttendanceListForExcel(AllAttendanceVO attendanceVO);

}
