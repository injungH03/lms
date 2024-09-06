package atos.lms.instructor.service;

import atos.lms.exam.service.GeneralModel;

@SuppressWarnings("serial")
public class InstructorVO extends InstructorMasterVO implements GeneralModel {
	
	private String id;
	private String password;
	private String name;
	private String gender;
	private String birthdate;
	private String phoneNo;
	private String email;
	private String zipcode;
	private String addr1;
	private String addr2;
	private String job;
	private String affiliation;
	private String department;
	private String position;
	private String bios;
	private String career;
	private String status;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getAffiliation() {
		return affiliation;
	}
	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getBios() {
		return bios;
	}
	public void setBios(String bios) {
		this.bios = bios;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "InstructorVO [id=" + id + ", password=" + password + ", name=" + name + ", gender=" + gender
				+ ", birthdate=" + birthdate + ", phoneNo=" + phoneNo + ", zipcode=" + zipcode + ", addr1=" + addr1
				+ ", addr2=" + addr2 + ", job=" + job + ", affiliation=" + affiliation + ", department=" + department
				+ ", position=" + position + ", bios=" + bios + ", career=" + career + ", status=" + status + "]";
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

	
	
}
