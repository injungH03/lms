package atos.lms.member.service;

import atos.lms.exam.service.GeneralModel;

@SuppressWarnings("serial")
public class CompanyVO implements GeneralModel {
	
	private String bizRegNo; //사업자번호
	private String corpName; //법인명(사업자명)
	private String repName; //대표자명
	private String bizType; //업태
	private String bizItem; //종목
	private String phoneNo; //대표전화번호
	private String addr1; //주소1
	private String addr2; //주소2
	
	
	public String getBizRegNo() {
		return bizRegNo;
	}
	public void setBizRegNo(String bizRegNo) {
		this.bizRegNo = bizRegNo;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
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
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
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
	@Override
	public String toString() {
		return "CompanyVO [bizRegNo=" + bizRegNo + ", corpName=" + corpName + ", refName=" + repName + ", bizType="
				+ bizType + ", bizItem=" + bizItem + ", phoneNo=" + phoneNo + ", addr1=" + addr1 + ", addr2=" + addr2
				+ "]";
	}
	public String getRepName() {
		return repName;
	}
	public void setRepName(String repName) {
		this.repName = repName;
	}
	
	
	

}
