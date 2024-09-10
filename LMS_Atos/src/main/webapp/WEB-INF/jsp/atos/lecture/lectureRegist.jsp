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

<div class="table-section">
<h2>과정개설</h2>
    <form method="post" action="your-action-url" enctype="multipart/form-data">
        <table class="search-table regist-table">
            <tr>
                <th>교육정보</th>
                <td>
                    <input type="button" class="btn btn-sm btn-primary" value="교육정보 선택" id="eduInfoButton" />
                    <span id="selectedEduInfo"></span>
                </td>
            </tr>
            <tr>
                <th>강사</th>
                <td>
                    <input type="button" class="btn btn-sm btn-primary" value="강사 선택" id="instructorButton" />
                    <span id="selectedInstructor"></span> 
                </td>
            </tr>
            <tr>
                <th>교육장소</th>
                <td>
				
				<input type="text" id="address" name="addr1" class="form-control me-2 custom-width" placeholder="주소를 검색해주세요" readonly />
				<button type="button" class="btn btn-sm btn-primary" id="addressSearchButton">주소검색</button><br />
				<input type="text"id="detailedAddress" name="addr2" class="form-control mt-2 custom-width" placeholder="상세주소를 입력하세요" />
                </td>
            </tr>
            <tr>
                <th>기수</th>
                <td>
                    <input type="text" name="term" class="form-control " maxlength="4" style="width:10%;" />
                </td>
            </tr>
            <tr>
                <th>인원수</th>
                <td>
                    <input type="number" name="numberOfPeople" class="form-control " min="1" max="100" style="width:10%;" />
                </td>
            </tr>
            <tr>
                <th>파일</th>
                <td>
		            <input name="file_1" id="egovComFileUploader" type="file" title="첨부파일" multiple />
		            <div id="egovComFileList" class="file-list"></div>
                </td>
            </tr>
            <tr>
                <th>접수 시작/종료일</th>
                <td>
                    <div class="d-flex">
                        <span>시작일 :</span>
                        <input type="date" name="regStartDate" class="form-control me-2 custom-date-picker" />
                        <span class="input-group-text calendar-icon"><i class="material-icons">calendar_today</i></span>
                        <span class="span-ml">종료일 :</span>
                        <input type="date" name="regEndDate" class="form-control me-2 custom-date-picker" />
                        <span class="input-group-text calendar-icon"><i class="material-icons">calendar_today</i></span>
                    </div>
                </td>
            </tr>
            <tr>
                <th>취소기간 시작/종료일</th>
                <td>
                    <div class="d-flex">
                        <span>시작일 :</span>
                        <input type="date" name="cancelStartDate" class="form-control me-2 custom-date-picker" />
                        <span class="input-group-text calendar-icon"><i class="material-icons">calendar_today</i></span>
                        <span class="span-ml">종료일 :</span>
                        <input type="date" name="cancelEndDate" class="form-control me-2 custom-date-picker" />
                        <span class="input-group-text calendar-icon"><i class="material-icons">calendar_today</i></span>
                    </div>
                </td>
            </tr>
            <tr>
                <th>학습 시작/종료일</th>
                <td>
                    <div class="d-flex">
                        <span>시작일 :</span>
                        <input type="date" name="learnStartDate" class="form-control me-2 custom-date-picker" />
                        <span class="input-group-text calendar-icon"><i class="material-icons">calendar_today</i></span>
                        <span class="span-ml">종료일 :</span>
                        <input type="date" name="learnEndDate" class="form-control me-2  custom-date-picker" />
                        <span class="input-group-text calendar-icon"><i class="material-icons">calendar_today</i></span>
                    </div>
                </td>
            </tr>

            <tr>
                <th>강의 차시</th>
                <td>
                    <button id="addLecture" class="btn btn-sm btn-primary">강의 차시 추가</button>
                </td>
            </tr>
        </table>

       <table class="table table-bordered mt-3">
            <thead>
                <tr>
                	<th>강의 차시</th>
                    <th>강의 차시 일자</th>
                    <th>교육 시간</th>
                    <th>시작 시간</th>
                    <th>종료 시간</th>
                    <th>관리</th>
                </tr>
            </thead>
            <tbody id="lectureForms">
                <!-- 동적으로 추가될 강의 차시 폼 영역 -->
            </tbody>
        </table>

        <!-- 등록 및 목록 버튼 -->
        <div class="d-flex justify-content-between mt-3">
            <button type="submit" class="btn btn-success">등록</button>
            <button type="button" class="btn btn-secondary" onclick="window.location.href='your-list-url'">목록</button>
        </div>
        
        <input type="hidden" id="atchPosblFileNumber" name="atchPosblFileNumber" value="<c:out value='${searchVO.atchPosblFileNumber}'/>" />
		<input type="hidden" name="atchPosblFileSize" value="<c:out value='${searchVO.atchPosblFileSize}'/>" />
    </form>
</div>

<script type="text/template" id="lectureTemplate">
    <tr>
        <td class="lecture-count">
            #차시#차
        </td>
        <td>
            <input type="date" name="lectureDate[]" class="form-control custom-date-picker date-fo" />
        </td>

        <td>
            <div class="radio-group">
                <label><input class="form-check-input" type="radio" name="eduTime_#index#" value="4"> 4시간</label>
                <label><input class="form-check-input" type="radio" name="eduTime_#index#" value="6"> 6시간</label>
                <label><input class="form-check-input" type="radio" name="eduTime_#index#" value="8"> 8시간</label>
            </div>
        </td>
        <td><input type="time" name="startTime[]" class="form-control" /></td>
        <td><input type="time" name="endTime[]" class="form-control" /></td>
        <td>
            <button type="button" class="btn btn-sm btn-danger remove-row">삭제</button>
        </td>
    </tr>
</script>

<!-- jQuery Script -->
<script>
$(document).ready(function() {
	//팝업 이동 URL
	$('#eduInfoButton').on('click', function() {
		 window.open("<c:url value='/education/educationPopup.do'/>", 'popup', 'width=1000,height=800');
	});
	$('#instructorButton').on('click', function() {
		 window.open("<c:url value='/education/instructorPopup.do'/>", 'popup', 'width=1000,height=800');
	});
	
	//다중 파일 업로드 처리
	var maxFileNum = document.getElementById('atchPosblFileNumber').value;
	if(maxFileNum==null || maxFileNum==""){
		maxFileNum = 3;
	}
	var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );
	multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );

	//강의 차시 추가
    var lectureIndex = 0;
    var lectureCount = 1;
    
    // 강의 차시 추가 버튼 클릭 시 동적으로 강의 차시 폼 추가
	$('#addLecture').on('click', function(event) {
        event.preventDefault();  // 버튼 클릭 시 페이지 새로고침 방지

        var template = $('#lectureTemplate').html();

        // 차시 번호 추가
        template = template.replace('#차시#', lectureCount++);

        // 강의 차시 추가 시 유일한 인덱스를 주입하여 라디오 버튼 이름을 구분
        template = template.replace(/#index#/g, lectureIndex++);

        // 강의 차시 폼을 테이블에 추가
        $('#lectureForms').append(template);
        
        // 새로 추가된 .custom-date-picker에 대해 flatpickr 적용
        $('#lectureForms').find('.custom-date-picker').flatpickr({
            dateFormat: 'Y-m-d',
            altInput: true,
            altFormat: 'Y-m-d',
        });
    });
    
	// 동적으로 생성된 강의 차시 행 삭제 기능
    $('#lectureForms').on('click', '.remove-row', function() {
        // 해당 행 삭제
        $(this).closest('tr').remove();
        
        // 차시 번호 재정렬
        reorderLectures();
    });

    // 차시 번호를 다시 정렬하는 함수
    function reorderLectures() {
        var count = 1;
        // 모든 강의 차시 행을 순회하면서 차시 번호를 업데이트
        $('#lectureForms').find('tr').each(function() {
            $(this).find('.lecture-count').text(count + '차');
            count++;
        });
        // lectureCount 값을 업데이트해서 새로운 행 추가 시 올바르게 번호를 부여
        lectureCount = count;
    }
    
    $('.custom-date-picker').flatpickr({
        dateFormat: 'Y-m-d',
        altInput: true,
        altFormat: 'Y-m-d',
    });

    // 달력 아이콘 클릭 시 달력 열기
    $('.calendar-icon').on('click', function() {
        $(this).prev('.custom-date-picker').flatpickr().open(); 
    });
    
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