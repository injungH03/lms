package atos.lms.education.service;

import java.time.LocalDate;

import atos.lms.exam.service.GeneralModel;

@SuppressWarnings("serial")
public class EducationVO extends EducationMasterVO implements GeneralModel {
	
	
	// atos_category_detail
	// atos_category_main
    // atos_category_sub
    private String code; // 교육 분류 정보 코드
    private String mainCode; // 대분류 코드
    private String subCode; // 중분류 코드
    private String mainName; // 대분류 명칭
    private String subName; // 중분류 명칭
    private String detailCode; // 소분류 코드
    private String detailName; // 소분류 명칭
    private String use_Yn; // 사용 여부
    
    
    // atos_education
    private int eduCode; // 코드
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
    
    
	@Override
	public String toString() {
		return "EducationVO [code=" + code + ", mainCode=" + mainCode + ", subCode=" + subCode + ", mainName="
				+ mainName + ", subName=" + subName + ", detailCode=" + detailCode + ", detailName=" + detailName
				+ ", use_Yn=" + use_Yn + ", eduCode=" + eduCode + ", title=" + title + ", category=" + category
				+ ", description=" + description + ", objective=" + objective + ", completionCriteria="
				+ completionCriteria + ", note=" + note + ", status=" + status + ", trainingTime=" + trainingTime
				+ ", regDate=" + regDate + ", register=" + register + "]";
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getMainCode() {
		return mainCode;
	}


	public void setMainCode(String mainCode) {
		this.mainCode = mainCode;
	}


	public String getSubCode() {
		return subCode;
	}


	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}


	public String getMainName() {
		return mainName;
	}


	public void setMainName(String mainName) {
		this.mainName = mainName;
	}


	public String getSubName() {
		return subName;
	}


	public void setSubName(String subName) {
		this.subName = subName;
	}


	public String getDetailCode() {
		return detailCode;
	}


	public void setDetailCode(String detailCode) {
		this.detailCode = detailCode;
	}


	public String getDetailName() {
		return detailName;
	}


	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}


	public String getUse_Yn() {
		return use_Yn;
	}


	public void setUse_Yn(String use_Yn) {
		this.use_Yn = use_Yn;
	}


	public int getEduCode() {
		return eduCode;
	}


	public void setEduCode(int eduCode) {
		this.eduCode = eduCode;
	}


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
