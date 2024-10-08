package atos.lms.schedule.service;

import java.time.LocalDate;

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


}
