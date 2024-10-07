package atos.lms.attendance.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import atos.lms.attendance.service.AllAttendanceExcelVO;
import atos.lms.attendance.service.AllAttendanceService;
import atos.lms.attendance.service.AllAttendanceVO;
import atos.lms.attendance.service.dao.AllAttendanceDAO;
import atos.lms.common.utl.ExcelUtil;

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
	public AllAttendanceVO selectAttendanceByCode(int attendCode) {
	    return allAttendanceDao.selectAttendanceByCode(attendCode);
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
	
	
    @Override
    public void attendanceListExcelDown(HttpServletResponse response, AllAttendanceVO attendanceVO) throws Exception {
        // 검색 조건을 반영한 출석 목록 조회
        List<AllAttendanceExcelVO> attendanceList = allAttendanceDao.selectAttendanceListForExcel(attendanceVO);

        // 엑셀 필드와 헤더 설정
        Map<String, String> fieldToHeaderMap = new LinkedHashMap<>();
        fieldToHeaderMap.put("corpName", "소속");
        fieldToHeaderMap.put("title", "교육명칭");
        fieldToHeaderMap.put("student", "수강생 아이디");
        fieldToHeaderMap.put("name", "수강생 이름");
        fieldToHeaderMap.put("status", "상태");
        fieldToHeaderMap.put("attendDate", "출석일");
        fieldToHeaderMap.put("inTime", "입실시간");
        fieldToHeaderMap.put("outTime", "퇴실시간");

        // 엑셀 파일로 출력
        ExcelUtil.exportToExcel(response, attendanceList, "출석목록", "출석목록엑셀파일", fieldToHeaderMap);
    }
}
