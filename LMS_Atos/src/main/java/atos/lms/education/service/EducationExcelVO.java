package atos.lms.education.service;

import java.time.LocalDate;

import atos.lms.exam.service.GeneralModel;

@SuppressWarnings("serial")
public class EducationExcelVO implements GeneralModel {
	
    private String title; // 명칭
    private String category; // 교육분류
    private String description; // 과정소개
    private String objective; // 과정목표
    private String completionCriteria; // 수료조건
    private String note; // 비고
    private String status; // 상태정보
    private String trainingTime; // 교육시간
    private LocalDate regDate; // 등록일
    private String register; // 등록자
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getObjective() {
		return objective;
	}
	public void setObjective(String objective) {
		this.objective = objective;
	}
	public String getCompletionCriteria() {
		return completionCriteria;
	}
	public void setCompletionCriteria(String completionCriteria) {
		this.completionCriteria = completionCriteria;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTrainingTime() {
		return trainingTime;
	}
	public void setTrainingTime(String trainingTime) {
		this.trainingTime = trainingTime;
	}
	public LocalDate getRegDate() {
		return regDate;
	}
	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}
	public String getRegister() {
		return register;
	}
	public void setRegister(String register) {
		this.register = register;
	}
    
   
    
}
