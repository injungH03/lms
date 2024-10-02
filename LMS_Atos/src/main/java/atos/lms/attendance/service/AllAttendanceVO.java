package atos.lms.attendance.service;

import java.time.LocalDate;
import java.time.LocalTime;

import atos.lms.exam.service.GeneralModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class AllAttendanceVO extends AllAttendanceMasterVO implements GeneralModel{
	
	/* atos_attendance */
	private int attendCode;        // 출석 코드
	private int lectureCode;       // 강의정보코드
	private String student;        // 학생아이디
	private LocalTime inTime;         // 입실시간
	private LocalTime outTime;        // 퇴실시간
	private LocalDate attendDate;  // 출석일
	private String status;         // 상태
	private LocalDate regDate;     // 등록일
	private String register;       // 등록자
	
	/* atos_student */
	private String id;            // 학생 아이디
	private String name;          // 이름
	
	/* atos_company */
	private String bizRegNo;      // 사업자등록번호
    private String corpName;      // 법인(단체)명
    
    /* atos_education */
    private int eduCode;          // 교육 코드
    private String title;         // 교육 명칭

}
