package atos.lms.company.service;

import java.time.LocalDate;

import atos.lms.exam.service.GeneralModel;

@SuppressWarnings("serial")
public class CompanyExcelVO implements GeneralModel {

	private String bizRegNo; // 사업자등록번호
	private String corpName; // 업체명(법인명)
	private String repName; // 대표자명
	private String bizType; // 업태
	private String bizItem; // 종목
	private String phoneNo; // 전화번호
	private String faxNo; // 팩스번호
	private String taxInvoice; // 세금계산서(이메일)
	private int empCount; // 직원수
	private int trainCount; // 교육인원수
	private String trainManager; // 교육담당자명
	private String trainEmail; // 교육담당자(이메일)
	private String trainPhone; // 교육담당자(전화번호)
	private String taxManager; // 세금계산서담당자명
	private String taxEmail; // 세금계산서담당자(이메일)
	private String taxPhone; // 세금계산서담당자(전화번호)
	private LocalDate regDate; // 등록일
	private String zipcode; // 우편번호
	private String addr1; // 주소
	private String addr2; // 상세주소

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

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public String getTaxInvoice() {
		return taxInvoice;
	}

	public void setTaxInvoice(String taxInvoice) {
		this.taxInvoice = taxInvoice;
	}

	public int getEmpCount() {
		return empCount;
	}

	public void setEmpCount(int empCount) {
		this.empCount = empCount;
	}

	public int getTrainCount() {
		return trainCount;
	}

	public void setTrainCount(int trainCount) {
		this.trainCount = trainCount;
	}

	public String getTrainManager() {
		return trainManager;
	}

	public void setTrainManager(String trainManager) {
		this.trainManager = trainManager;
	}

	public String getTrainEmail() {
		return trainEmail;
	}

	public void setTrainEmail(String trainEmail) {
		this.trainEmail = trainEmail;
	}

	public String getTrainPhone() {
		return trainPhone;
	}

	public void setTrainPhone(String trainPhone) {
		this.trainPhone = trainPhone;
	}

	public String getTaxManager() {
		return taxManager;
	}

	public void setTaxManager(String taxManager) {
		this.taxManager = taxManager;
	}

	public String getTaxEmail() {
		return taxEmail;
	}

	public void setTaxEmail(String taxEmail) {
		this.taxEmail = taxEmail;
	}

	public String getTaxPhone() {
		return taxPhone;
	}

	public void setTaxPhone(String taxPhone) {
		this.taxPhone = taxPhone;
	}

	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
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

}
