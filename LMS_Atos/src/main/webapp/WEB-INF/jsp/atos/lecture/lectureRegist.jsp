<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/lecture/lecture.css' />">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/utl/EgovCmmUtl.js'/>" ></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<div class="head-section">
	<span>&nbsp;과정개설</span>
</div>
<div class="table-section">

    <form method="post" action="your-action-url" enctype="multipart/form-data">
        <table class="search-table regist-table">
            <tr>
                <th>교육정보</th>
                <td colspan="3">
                    <input type="button" class="btn btn-sm btn-primary" value="교육정보 선택" id="eduInfoButton" />
                    <span id="selectedEduInfo"></span>
                </td>
            </tr>
            <tr>
                <th>강사</th>
                <td colspan="3">
                    <input type="button" class="btn btn-sm btn-primary" value="강사 선택" id="instructorButton" />
                    <span id="selectedInstructor"></span> 
                </td>
            </tr>
            <tr>
                <th>교육장소</th>
                <td colspan="3">
				
				<input type="text" id="address" name="addr1" class="form-control me-2 custom-width" placeholder="주소를 검색해주세요" readonly />
				<button type="button" class="btn btn-sm btn-primary" id="addressSearchButton">주소검색</button><br />
				<input type="text"id="detailedAddress" name="addr2" class="form-control mt-2 custom-width" placeholder="상세주소를 입력하세요" />
                </td>
            </tr>



            <tr>
                <th>접수 시작일</th>
                <td>
                    <div class="d-flex">
                        <input type="date" name="regStartDate" class="form-control me-2 custom-date-picker" />
                    </div>
                </td>
                <th>접수 종료일</th>
                <td>
                    <div class="d-flex">
                        <input type="date" name="regEndDate" class="form-control me-2 custom-date-picker" />
                    </div>
                </td>
 
            </tr>
            <tr>
            	<th>과정 날짜</th>
                <td>
                    <div class="d-flex">
                        <input type="date" name="learnStartDate" class="form-control me-2 custom-date-picker" />
                    </div>
                </td>
                <th>모집 인원수</th>
                <td>
                    <input type="number" name="numberOfPeople" class="form-control custom-width" min="1" max="100"  />
                </td>  
            </tr>
            <tr>
                <th>시작시간</th>
                <td><input type="time" name="" class="form-control custom-width" /></td>
                <th>종료시간</th>
                <td><input type="time" name="" class="form-control custom-width" /></td>
            </tr>
            <tr>
            	<th>담당자</th>
            	<td><input type="text" name="" class="form-control " placeholder="담당자를 입력하세요" /></td>
                <th>연락처</th>
            	<td><input type="text" name="" class="form-control " placeholder="연락처를 입력하세요" /></td>
            </tr>
			<tr>
			    <th>파일</th>
			    <td colspan="3">
			        <input name="file_1" id="egovComFileUploader" class="file-button" type="file" title="첨부파일" multiple />
			        <div id="egovComFileList" class="file-list"></div>
			    </td>
			</tr>

        </table>

        <!-- 등록 및 목록 버튼 -->
        <div class="d-flex justify-content-between mt-3">
            <button type="submit" class="btn btn-success">등록</button>
            <button type="button" class="btn btn-secondary">목록</button>
        </div>
        
        <input type="hidden" id="atchPosblFileNumber" name="atchPosblFileNumber" value="<c:out value='${searchVO.atchPosblFileNumber}'/>" />
		<input type="hidden" name="atchPosblFileSize" value="<c:out value='${searchVO.atchPosblFileSize}'/>" />
    </form>
</div>


<!-- jQuery Script -->
<script>
$(document).ready(function() {
	//팝업 이동 URL
	$('#eduInfoButton').on('click', function() {
		 window.open("<c:url value='/education/educationPopup.do'/>", 'popup', 'width=1200,height=800');
	});
	$('#instructorButton').on('click', function() {
		 window.open("<c:url value='/education/instructorPopup.do'/>", 'popup', 'width=1200,height=800');
	});
	
	//다중 파일 업로드 처리
	var maxFileNum = document.getElementById('atchPosblFileNumber').value;
	if(maxFileNum==null || maxFileNum==""){
		maxFileNum = 3;
	}
	var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );
	multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );

    $('#addressSearchButton').on('click', function() {
        new daum.Postcode({
            oncomplete: function(data) {
                $('#address').val(data.address); // 기본 주소
                $('#detailedAddress').focus();
            }
        }).open();
    });
    
});
</script>