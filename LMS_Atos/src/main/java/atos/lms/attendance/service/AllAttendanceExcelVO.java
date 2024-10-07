package atos.lms.attendance.service;

import java.time.LocalDate;
import java.time.LocalTime;

import atos.lms.exam.service.GeneralModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class AllAttendanceExcelVO  implements GeneralModel {
	
    private String corpName;     // 소속
    private String title;        // 교육명칭
    private String student;      // 수강생 아이디
    private String name;         // 수강생 이름
    private String status;       // 상태명
    private LocalDate attendDate; // 출석일
    private LocalTime inTime;    // 입실시간
    private LocalTime outTime;   // 퇴실시간
	

}
