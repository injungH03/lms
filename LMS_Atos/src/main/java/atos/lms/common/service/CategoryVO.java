package atos.lms.common.service;

import java.util.List;

import atos.lms.exam.service.GeneralModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@SuppressWarnings("serial")
public class CategoryVO implements GeneralModel {

	private String code;
	private String mainCode;
	private String subCode;
	private String mainName;
	private String subName;
	private String useYn;
	private String regDate;
	private String register;

	// 검색 조건 부분
	@Builder.Default
	private String checkYn = "Y";
	
	private List<String> mtype;
	private List<String> stype;

	/*	*//**
			 * @return 사용여부 기본값 = Y
			 */
	/*
	 * public String getCheckYn() { return checkYn; }
	 * 
	 *//**
		 * @param checkYn 사용여부 기본값 = Y
		 */
	/*
	 * public void setCheckYn(String checkYn) { this.checkYn = checkYn; }
	 * 
	 *//**
		 * @return 카테고리 메인코드
		 */
	/*
	 * public List<String> getMtype() { return mtype; }
	 * 
	 *//**
		 * @param mtype 카테고리 메인코드
		 */
	/*
	 * public void setMtype(List<String> mtype) { this.mtype = mtype; }
	 * 
	 *//**
		 * @return 카테고리 서브코드
		 */
	/*
	 * public List<String> getStype() { return stype; }
	 * 
	 *//**
		 * @param stype 카테고리 서브코드
		 *//*
			 * public void setStype(List<String> stype) { this.stype = stype; }
			 * 
			 * public String getCode() { return code; }
			 * 
			 * public void setCode(String code) { this.code = code; }
			 * 
			 * public String getMainCode() { return mainCode; }
			 * 
			 * public void setMainCode(String mainCode) { this.mainCode = mainCode; }
			 * 
			 * public String getSubCode() { return subCode; }
			 * 
			 * public void setSubCode(String subCode) { this.subCode = subCode; }
			 * 
			 * public String getMainName() { return mainName; }
			 * 
			 * public void setMainName(String mainName) { this.mainName = mainName; }
			 * 
			 * public String getSubName() { return subName; }
			 * 
			 * public void setSubName(String subName) { this.subName = subName; }
			 * 
			 * public String getUseYn() { return useYn; }
			 * 
			 * public void setUseYn(String useYn) { this.useYn = useYn; }
			 * 
			 * public String getRegDate() { return regDate; }
			 * 
			 * public void setRegDate(String regDate) { this.regDate = regDate; }
			 * 
			 * public String getRegister() { return register; }
			 * 
			 * public void setRegister(String register) { this.register = register; }
			 */

}
