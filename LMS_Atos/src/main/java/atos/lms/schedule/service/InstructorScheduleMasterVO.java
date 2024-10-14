package atos.lms.schedule.service;

import atos.lms.exam.service.GeneralModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class InstructorScheduleMasterVO implements GeneralModel {
	
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
    
    /** CURD 타입 */
    private String type;

}
