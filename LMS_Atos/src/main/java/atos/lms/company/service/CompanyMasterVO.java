package atos.lms.company.service;

import java.util.List;

import atos.lms.exam.service.GeneralModel;

@SuppressWarnings("serial")
public class CompanyMasterVO implements GeneralModel {

	/** 현재페이지 */
	private int pageIndex = 1;

	/** 페이지개수 */
	private int pageUnit = 10;

	/** 페이지사이즈 */
	private int pageSize = 10;

	/** 첫페이지 인덱스 */
	private int firstIndex = 1;

	/** 마지막페이지 인덱스 */
	private int lastIndex = 1;

	/** 페이지당 레코드 개수 */
	private int recordCountPerPage = 10;

	/** 레코드 번호 */
	private int rowNo = 0;

	/** 검색조건 */
	private String searchCnd = "";

	/** 상태 이름 */
	private String statusName = "";

	/** 상태 코드 */
	private String statusCode = "";

	/** 회사 이름 */
	private String corpName = "";

	/** 회사 사업자번호 */
	private String corpBiz = "";

	/** 검색단어 */
	private String searchWrd = "";

	/** 첨부가능파일숫자 */
	private int atchPosblFileNumber;

	/** 첨부가능파일사이즈 */
	private String atchPosblFileSize = "";

	/** 여러 사업자등록번호를 처리할 리스트 */
	private List<String> corpBizList; 
	
	private String companyStatus;
	

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}

	public String getSearchCnd() {
		return searchCnd;
	}

	public void setSearchCnd(String searchCnd) {
		this.searchCnd = searchCnd;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getCorpBiz() {
		return corpBiz;
	}

	public void setCorpBiz(String corpBiz) {
		this.corpBiz = corpBiz;
	}

	public String getSearchWrd() {
		return searchWrd;
	}

	public void setSearchWrd(String searchWrd) {
		this.searchWrd = searchWrd;
	}

	public int getAtchPosblFileNumber() {
		return atchPosblFileNumber;
	}

	public void setAtchPosblFileNumber(int atchPosblFileNumber) {
		this.atchPosblFileNumber = atchPosblFileNumber;
	}

	public String getAtchPosblFileSize() {
		return atchPosblFileSize;
	}

	public void setAtchPosblFileSize(String atchPosblFileSize) {
		this.atchPosblFileSize = atchPosblFileSize;
	}

	public List<String> getCorpBizList() {
		return corpBizList;
	}

	public void setCorpBizList(List<String> corpBizList) {
		this.corpBizList = corpBizList;
	}

	public String getCompanyStatus() {
		return companyStatus;
	}

	public void setCompanyStatus(String companyStatus) {
		this.companyStatus = companyStatus;
	}

	
	
}
