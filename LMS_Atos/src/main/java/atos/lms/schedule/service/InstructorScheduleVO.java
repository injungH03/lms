package atos.lms.schedule.service;

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
public class InstructorScheduleVO extends InstructorScheduleMasterVO implements GeneralModel{
	
	private int scheduleCode;
	private String id;
	private LocalDate scheduleDate;
	private String mainEvent;
	private String subEvent;
    private LocalTime startTime;        
    private LocalTime endTime;          
	
    // atos_instructor
	private String name;
	


}
