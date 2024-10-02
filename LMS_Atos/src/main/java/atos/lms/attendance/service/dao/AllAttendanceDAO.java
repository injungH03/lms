package atos.lms.attendance.service.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import atos.lms.attendance.service.AllAttendanceVO;

@Repository
public interface AllAttendanceDAO {
	
    // 출석 목록 조회
    List<AllAttendanceVO> selectAttendanceList(AllAttendanceVO attendanceVO);
    
    // 출석 목록 총 개수 조회 (페이징을 위해)
    int selectAttendanceListCnt(AllAttendanceVO attendanceVO);

}
	