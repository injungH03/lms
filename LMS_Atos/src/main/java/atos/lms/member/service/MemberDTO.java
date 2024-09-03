package atos.lms.member.service;

import atos.lms.exam.service.GeneralModel;

@SuppressWarnings("serial")
public class MemberDTO implements GeneralModel {
	
	private String id;
	private String name;
	private String birthdate;
	private String phoneNo;
	private String zipcode;
	private String addr1;
	private String addr2;
	private String bizRegNo;
	private String status;
	private String department;
	private String position;
	private String email;
	
	private String companybizRegNo; //사업자번호
	private String corpName; //법인명(사업자명)
	private String repName; //대표자명
	private String bizType; //업태
	private String bizItem; //종목
	private String companyPhoneNo; //대표전화번호
	private String companyZipcode; //회사우편번호
	private String companyAddr1; //주소1
	private String companyAddr2; //주소2
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompanybizRegNo() {
		return companybizRegNo;
	}
	public void setCompanybizRegNo(String companybizRegNo) {
		this.companybizRegNo = companybizRegNo;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public String getRepName() {
		return repName;
	}
	public void setRepName(String repName) {
		this.repName = repName;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public String getBizItem() {
		return bizItem;
	}
	public void setBizItem(String bizItem) {
		this.bizItem = bizItem;
	}
	public String getCompanyPhoneNo() {
		return companyPhoneNo;
	}
	public void setCompanyPhoneNo(String companyPhoneNo) {
		this.companyPhoneNo = companyPhoneNo;
	}
	public String getCompanyZipcode() {
		return companyZipcode;
	}
	public void setCompanyZipcode(String companyZipcode) {
		this.companyZipcode = companyZipcode;
	}
	public String getCompanyAddr1() {
		return companyAddr1;
	}
	public void setCompanyAddr1(String companyAddr1) {
		this.companyAddr1 = companyAddr1;
	}
	public String getCompanyAddr2() {
		return companyAddr2;
	}
	public void setCompanyAddr2(String companyAddr2) {
		this.companyAddr2 = companyAddr2;
	}

	
	
	
}
