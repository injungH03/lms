package atos.lms.member.service;

import atos.lms.exam.service.GeneralModel;

@SuppressWarnings("serial")
public class MemberVO extends MemberMasterVO implements GeneralModel {
	
	private String id;
	private String password;
	private String name;
	private String gender;
	private String birthdate;
	private String phoneNo;
	private String zipcode;
	private String addr1;
	private String addr2;
	private String bizRegNo;
	private String status;
	
	
	
	
	@Override
	public String toString() {
		return "StudentVO [id=" + id + ", password=" + password + ", name=" + name + ", gender=" + gender
				+ ", birthdate=" + birthdate + ", phoneNo=" + phoneNo + ", zipcode=" + zipcode + ", addr1=" + addr1
				+ ", addr2=" + addr2 + ", bizRegNo=" + bizRegNo + ", status=" + status + "]";
	}
	
	
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
	public String getBizRegNo() {
		return bizRegNo;
	}
	public void setBizRegNo(String bizRegNo) {
		this.bizRegNo = bizRegNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
