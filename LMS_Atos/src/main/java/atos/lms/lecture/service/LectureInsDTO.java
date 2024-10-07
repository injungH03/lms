package atos.lms.lecture.service;


import atos.lms.exam.service.GeneralModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class LectureInsDTO extends LectureMasterVO implements GeneralModel {
	
    private int lectureCode;         // 강의정보코드
    private String instructorName;   // 강사 이름
    private String id;               // 강사 아이디
    private String phoneNo;          // 전화번호
    private String email;            // 이메일
    private String department;       // 소속
    private String position;         // 직책


}
