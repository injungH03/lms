package atos.lms.education.service;

import java.util.List;

import atos.lms.exam.service.GeneralModel;

@SuppressWarnings("serial")
public class EducationMasterVO implements GeneralModel {
	
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

	private List<String> corpBizList;  // 여러 사업자등록번호를 처리할 리스트
	
	private String companyStatus;


    private String trainingTimeName; // 교육 시간 명칭

    private List<Integer> eduCodeList;  // 여러 개의 교육 코드 처리

    // 수료 조건 관련 필드
    private String completionCode; // 수료 조건 코드
    private String completionRate; // 진도율
    private String completionScore; // 시험 점수
    private String completionSurvey; // 설문 유무
    
   
    
    public String getCompletionCode() {
		return completionCode;
	}

	public void setCompletionCode(String completionCode) {
		this.completionCode = completionCode;
	}

	public String getCompletionRate() {
		return completionRate;
	}

	public void setCompletionRate(String completionRate) {
		this.completionRate = completionRate;
	}

	public String getCompletionScore() {
		return completionScore;
	}

	public void setCompletionScore(String completionScore) {
		this.completionScore = completionScore;
	}

	public String getCompletionSurvey() {
		return completionSurvey;
	}

	public void setCompletionSurvey(String completionSurvey) {
		this.completionSurvey = completionSurvey;
	}

	public List<Integer> getEduCodeList() {
        return eduCodeList;
    }

    public void setEduCodeList(List<Integer> eduCodeList) {
        this.eduCodeList = eduCodeList;
    }
    
    
    public String getTrainingTimeName() {
        return trainingTimeName;
    }

    public void setTrainingTimeName(String trainingTimeName) {
        this.trainingTimeName = trainingTimeName;
    }
	

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
