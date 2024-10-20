package atos.lms.schedule.service;

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
public class InstructorScheduleVO extends InstructorScheduleMasterVO implements GeneralModel{
	
	private int scheduleCode;
	private String id;
	private String mainEvent;
	private String description;
    private String startDate;        
    private String endDate;
    private int allDay;
    private String scheduleColor;
	
    // atos_instructor
	private String name;


	


}
