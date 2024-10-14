package atos.lms.lecture.service;


import java.util.List;

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
public class LectureMemDTO extends LectureMasterVO implements GeneralModel {
	
	private String memberId;
	private String memName;
	private String bizRegNo;
	private String corpName;
	private String phoneNo;
	private int lectureCode;
	private int enrollCode;
	private String corpBiz;
	
	private List<String> memberIds;
	private List<Integer> enrollIds;
	
	
}
