<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/atos/lecture/lecture.css' />">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<div class="head-section">
	<span>&nbsp;2024년 관리감독자 (제조업)</span>
</div>
<div class="table-section">
	<%@ include file="/WEB-INF/jsp/atos/common/menutab.jsp" %>
    <form method="get" action="">
        <table class="search-table">
            <tr>
                <th class="custom-th-width">수료여부</th>
                <td colspan="2">
                    <div class="">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="status" value="전체" checked>
                            <label class="form-check-label">전체</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="status" value="대기">
                            <label class="form-check-label">수료</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="status" value="신청기간">
                            <label class="form-check-label">미수료</label>
                        </div>
                    </div>
                </td>
				<th class="custom-th-width">과정날짜</th>
                <td colspan="3">
                    <div class="d-flex">
                        <input type="date" name="learningStartDate" id="learningStartDate" class="form-control me-2 custom-date-picker" />
                        <span class="input-group-text calendar-icon"><i class="material-icons">calendar_today</i></span>
                    </div>
                </td>
            <tr>
                <th>검색</th>
                <td colspan="5">
                    <div class="d-flex">
                        <select name="searchType" class="form-select search-select me-2">
                            <option value="전체">전체</option>
                        </select>
                        <input type="text" name="searchKeyword" class="form-control search-input me-2" placeholder="검색어를 입력하세요" />
                        <button type="submit" class="btn-search">검색</button>
                    </div>
                </td>
            </tr>
        </table>
    </form>


<!-- 테이블 위에 버튼 섹션 -->
<div class="d-flex justify-content-between mb-2 mt-5">
    <div>
        Total: <strong>38건</strong>
    </div>
    <div>
    	<button class="btn-create-course" id="studentAdd">수강생추가</button>
        <button class="btn-excel">EXCEL</button>
        <button class="btn-del" id="">삭제</button>
    </div>
</div>

<!-- 과정 테이블 섹션 -->
<div class="course-table-section">
    <table class="table table-bordered">
       <colgroup>
	        <col style="width: 5%;">
	        <col style="width: 20%;">
	        <col style="width: 10%;">
	        <col style="width: 20%;">
	        <col style="width: 15%;">
	        <col style="width: 15%;">
	        <col style="width: 5%;">
    	</colgroup>
        <thead>
            <tr>
                <th>No</th>
                <th>과정명</th>
                <th>소속</th>
                <th>수강생</th>
                <th>과정날짜</th>
                <th>등록일</th>
                <th>수료</th>
                <th><input type="checkbox" id="checkAll"></th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>1</td>
                <td>테스트 과정 1</td>
                <td>아토스</td>
                <td><a href="#" class="student-link" data-student-id="test">아이디/이름</a></td>
                <td>2024.09.11</td>
                <td>2024.09.25</td>
                <td>수료</td>
                <td><input type="checkbox" name="rowCheck" value=""></td>
            </tr>

        </tbody>
    </table>
</div>
</div>
<script>
$(document).ready(function() {
	$('#studentAdd').on('click', function() {
		 window.open("<c:url value='/education/studentListPopup.do'/>", 'popup', 'width=1200,height=800');
	});
	
    $('.student-link').on('click', function(e) {
        e.preventDefault(); 

        var studentId = $(this).data('student-id');
		alert("팝업 테스트 = " + studentId)
        // 팝업 창 열기
        var popupUrl = "<c:url value='/education/studentDetailPopup.do'/>?studentId=" + encodeURIComponent(studentId);
        var popupName = '수강생 정보';
        var popupFeatures = 'width=1200,height=800,scrollbars=yes,resizable=yes';

        window.open(popupUrl, popupName, popupFeatures);
    });
	
	
    // 전체 선택/해제 기능
    $('#checkAll').on('click', function() {
        $('tbody input[name="rowCheck"]').prop('checked', this.checked);
    });
	
    $('.custom-date-picker').flatpickr({
        dateFormat: 'Y-m-d',
        altInput: true,
        altFormat: 'Y-m-d',
    });

    // 달력 아이콘 클릭 시 달력 열기
    $('.calendar-icon').on('click', function() {
        $(this).prev('.custom-date-picker').flatpickr().open(); 
    });
});
</script>