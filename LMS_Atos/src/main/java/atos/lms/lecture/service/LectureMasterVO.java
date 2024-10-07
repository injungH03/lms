package atos.lms.lecture.service;

import java.util.List;

import atos.lms.exam.service.GeneralModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")
public class LectureMasterVO implements GeneralModel {
	
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
    
    /** 상태 이름*/
    private String statusName = "";
    
    /** 상태 코드*/
    private String statusCode = "";
    
	/** 검색단어 */
    private String searchWrd = "";
    
	/** 첨부가능파일숫자 */
    private int atchPosblFileNumber;
    
    /** 첨부가능파일사이즈 */
    private String atchPosblFileSize = "";
    
    /** 정렬 필드 */
    private String sortField;
    
    /** 정렬 순서 */
    private String sortOrder;
    
    /** CURD 타입 */
    private String type;
	
    /** 카테고리 검색 메인 코드*/
    private String srcMainCode;
    
    /** 신청시작일 */
    private String srcStartDate;
    
    /** 신청종료일 */
    private String srcEndDate;
    
    /** 과정날짜*/
    private String srcLearnDate;

    

}
